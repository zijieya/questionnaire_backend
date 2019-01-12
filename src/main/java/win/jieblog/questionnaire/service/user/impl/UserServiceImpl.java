package win.jieblog.questionnaire.service.user.impl;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import win.jieblog.questionnaire.dao.UserMapper;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.session.RegisterRequest;
import win.jieblog.questionnaire.model.contract.session.RegisterResponse;
import win.jieblog.questionnaire.model.contract.user.ResetPasswordRequest;
import win.jieblog.questionnaire.model.contract.user.ResetPasswordResponse;
import win.jieblog.questionnaire.model.contract.user.UploadAvatarRequest;
import win.jieblog.questionnaire.model.contract.user.UploadAvatarResponse;
import win.jieblog.questionnaire.model.entity.User;
import win.jieblog.questionnaire.service.user.UserService;
import win.jieblog.questionnaire.utils.AvatarHelper;
import win.jieblog.questionnaire.utils.LogHelper;
import win.jieblog.questionnaire.utils.SerialsIdHelper;

import java.io.*;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
     private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private AvatarHelper avatarHelper;

    /**
     * 注册
     * @param request
     * @return
     * @throws NotFoundException
     */
    @Override
    public RegisterResponse register(RegisterRequest request) throws NotFoundException {
        request.validate();
        RegisterResponse response=new RegisterResponse();
        // contract转entity
        User user=new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setAvatar(avatarHelper.getGravatarUrl(request.getEmail()));
        //校验用户名和邮箱是否已经存在
        List<User> list =userMapper.getUserByEmailOrUsername(request.getEmail(),request.getUsername());
        if (list.size()>0){
            logger.error(LogHelper.LogStatement(request.getUsername(),"注册","失败","已存在此用户名或邮箱"));
            throw new NotFoundException("已存在此用户名或邮箱", ErrorCode.USER_NOT_FOUND.getCode());
        }
        else
        {
            user.setPassword(request.getPassword());
            user.setUserserialid(SerialsIdHelper.getSerialsId());
            int count=userMapper.insertSelective(user);//影响的条数
            logger.info("增加用户"+user.getUsername());
            logger.info(user.getUsername(),"注册","成功");
            response.setSuccessful(true);
        }
        return response;
    }

    /**
     * 重置密码
     * @param request
     * @return
     * @throws DataBaseErrorException
     */
    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) throws DataBaseErrorException, NotFoundException {
        request.validate();
        ResetPasswordResponse response=new ResetPasswordResponse();
        User user=new User();
        user.setUserserialid(request.getUserserialid());
        user.setPassword(request.getPassword());
        int count=userMapper.updateByPrimaryKeySelective(user);
        if (count!=1){
            logger.error(LogHelper.LogStatement(request.getUserserialid(),"重置密码","失败","数据库更新异常"));
            throw new DataBaseErrorException("更新异常",ErrorCode.UPDATE_ERROR.getCode());
        }
        logger.info(LogHelper.LogStatement(request.getUserserialid(),"重置密码","成功"));
        response.setSuccessful(true);
        return response;
    }

    /**
     * 上传头像
     * @param request
     * @return
     * @throws IOException
     * @throws NotFoundException
     * @throws DataBaseErrorException
     */
    @Override
    public UploadAvatarResponse uploadAvatar(UploadAvatarRequest request) throws IOException, NotFoundException, DataBaseErrorException {
        request.validate();
        UploadAvatarResponse uploadAvatarResponse=new UploadAvatarResponse();
        // 用httpget下载文件
        HttpGet httpGet=new HttpGet(request.getAvatarUrl());
        HttpResponse response = HttpClients.createDefault().execute(httpGet);
        if (response==null||response.getStatusLine()==null){
            logger.error(LogHelper.LogStatement(request.getUserserialid(),"上传头像","失败","文件下载请求失败"));
            throw new NotFoundException("文件下载请求失败",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }else if (response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
            logger.error(LogHelper.LogStatement(request.getUserserialid(),"上传头像","失败","文件下载请求失败"));
            throw new NotFoundException("文件下载请求失败",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        else
        {
            byte[] byteFile= EntityUtils.toByteArray(response.getEntity());
            File file=new File(request.getAvatarName());
            OutputStream output = new FileOutputStream(file);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(byteFile);
            String avatarUrl=avatarHelper.getOssUrl(request.getAvatarName(),file);
            // 更新数据库url
            User user=new User();
            user.setUserserialid(request.getUserserialid());
            user.setAvatar(avatarUrl);
            int total=userMapper.updateByPrimaryKeySelective(user);
            if (total!=1){
                logger.error(LogHelper.LogStatement(request.getUserserialid(),"上传头像","失败","数据库更新异常"));
                throw new DataBaseErrorException("更新异常",ErrorCode.UPDATE_ERROR.getCode());
            }
            uploadAvatarResponse.setSuccessful(true);
            logger.info(LogHelper.LogStatement(request.getUserserialid(),"上传头像","成功"));
            return uploadAvatarResponse;
        }
    }
}
