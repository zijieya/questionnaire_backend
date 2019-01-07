package win.jieblog.questionnaire.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import win.jieblog.questionnaire.dao.UserMapper;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.common.*;
import win.jieblog.questionnaire.model.contract.user.GetUserInfoRequest;
import win.jieblog.questionnaire.model.contract.user.GetUserInfoResponse;
import win.jieblog.questionnaire.model.entity.User;
import win.jieblog.questionnaire.service.CommonService;
import win.jieblog.questionnaire.utils.AvatarHelper;
import win.jieblog.questionnaire.utils.JwtHelper;
import win.jieblog.questionnaire.utils.LogHelper;
import win.jieblog.questionnaire.utils.SerialsIdHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CommonServiceImpl implements CommonService {
    private final static long ttlTime=60*1000*60*24;//token的有效期为一天
    private static ObjectMapper MAPPER = new ObjectMapper();
    Logger logger=LoggerFactory.getLogger(CommonServiceImpl.class);
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MailSender mailSender;
    @Autowired
    private Environment env;
    @Autowired
    private AvatarHelper avatarHelper;
    /**
     *
     * @param resp httpservlet
     * @param request 请求
     * @return
     * @throws NotFoundException
     * @throws JsonProcessingException
     */
    @Override
    public LoginResponse getLogin(HttpServletResponse resp, LoginRequest request) throws NotFoundException, JsonProcessingException {
        JwtHelper jwtHelper=new JwtHelper();
        User user=userMapper.getUserByLogin(request.getUsername(),request.getPassword());
        LoginResponse response=new LoginResponse();
        if (user==null){
            logger.error(LogHelper.LogStatement(request.getUsername(),"登录","失败","用户名或密码错误"));
            throw new NotFoundException("用户名或密码错误", ErrorCode.USER_NOT_FOUND.getCode());
        }
        else
        {
            response.setSuccessful(true);
            //设置token
            Map<String,String> map=new HashMap<>();
            map.put("username",user.getUsername());
            String subject=MAPPER.writeValueAsString(map);
            String token=jwtHelper.createToken(user.getUserserialid(),subject,ttlTime);
            resp.setHeader("Authorization","Bearer "+token);
            //存入 redis user到token
            template.opsForHash().put("token",request.getUsername(),token);
            //存入redis token到user
            template.opsForHash().put("tokentouser",token,request.getUsername());
            //logger.info("存入"+user.getUsername()+"token到redis");
            logger.info(LogHelper.LogStatement(user.getUsername(),"登录","成功"));
        }
        return response;
    }

    /**
     * 注册用户
     * @param request
     * @return
     * @throws NotFoundException
     */
    public RegisterResponse register(RegisterRequest request) throws NotFoundException{
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
            throw new NotFoundException("已存在此用户名或邮箱",ErrorCode.USER_NOT_FOUND.getCode());
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
     * 发送验证码
     * @param request
     * @return
     * @throws NotFoundException
     */
   public SendMailResponse sendMail(SendMailRequest request) throws NotFoundException {
        // 验证邮箱是否存在 重用 getUserByEmailOrUsername
       List<User> list =userMapper.getUserByEmailOrUsername(request.getEmail(),null);
       if (list.size()==0){
           logger.error(LogHelper.LogStatement("系统","查找邮箱","失败","找不到该邮箱"));
           throw new NotFoundException("找不到该邮箱",ErrorCode.USER_NOT_FOUND.getCode());

       }
       int code=(int)((Math.random()*9+1)*100000);
        SendMailResponse response=new SendMailResponse();
       SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
       simpleMailMessage.setTo(request.getEmail());
       simpleMailMessage.setText("验证码为"+code+"(有效期5分钟)");
       simpleMailMessage.setFrom(env.getProperty("spring.mail.username"));
       simpleMailMessage.setSubject("验证码");
       mailSender.send(simpleMailMessage);
       logger.info(LogHelper.LogStatement("系统","发送邮件验证码","成功"));

       //验证码保存5分钟
       template.opsForValue().set(list.get(0).getUserserialid(),code,5, TimeUnit.MINUTES);
       response.setEmail(request.getEmail());
       response.setSuccessful(true);
       response.setUserserialid(list.get(0).getUserserialid());
       return response;
   }

    /**
     * 验证码校验
     * @param request
     * @return
     * @throws NotFoundException
     */
   public ActiveCodeResponse activeCode(ActiveCodeRequest request) throws NotFoundException{
        ActiveCodeResponse response=new ActiveCodeResponse();
        Integer code= (Integer) template.opsForValue().get(request.getUserserialid());
        // 仅仅表明进行了redis操作
        logger.info(LogHelper.LogStatement("系统","从redis中查找用户序列号为"+request.getUserserialid()+"的验证码","成功"));
        if (code==null){
            logger.error(LogHelper.LogStatement(request.getUserserialid(),"验证码校验","失败","验证码失效"));
            throw new NotFoundException("验证码失效",ErrorCode.ACTIVECODE_NOT_FOUND.getCode());
        }
        if (code!=request.getVerificationCode()){
            logger.error(LogHelper.LogStatement(request.getUserserialid(),"验证码校验","失败","验证码错误"));
            throw new NotFoundException("验证码错误",ErrorCode.ACTIVECODE_NOT_FOUND.getCode());
        }
        response.setSuccessful(true);
        response.setUserserialid(request.getUserserialid());
        logger.info(LogHelper.LogStatement(request.getUserserialid(),"验证码校验","成功"));
        return response;
    }

    /**
     * 重置密码
     * @param request
     * @return
     * @throws DataBaseErrorException
     */
    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) throws DataBaseErrorException {
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
     * 通过token拉取用户信息
     * @param request
     * @return
     * @throws NotFoundException
     */
    @Override
    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) throws NotFoundException {
        GetUserInfoResponse response=new GetUserInfoResponse();
       String username= (String) template.opsForHash().get("tokentouser",request.getToken());
       logger.info(username);
       if (username==null){
           logger.error(LogHelper.LogStatement("token为"+request.getToken(),"通过token拉取用户信息","失败","token不存在"));
           throw new NotFoundException("token不存在",ErrorCode.EMPTY_TOKEN.getCode());
       }

       User user=userMapper.getUserByEmailOrUsername("",username).get(0);
       response.setUsername(user.getUsername());
       response.setAccess(user.getRole());
       response.setAvatar(user.getAvatar());
       response.setUserId(user.getUserid());
       response.setSuccessful(true);
        logger.error(LogHelper.LogStatement("token为"+request.getToken(),"通过token拉取用户信息","成功"));
        return response;
    }

    /**
     * 更新头像
     * @param request
     * @return
     * @throws IOException
     * @throws NotFoundException
     * @throws DataBaseErrorException
     */
    @Override
    public UploadAvatarResponse uploadAvatar(UploadAvatarRequest request) throws IOException, NotFoundException, DataBaseErrorException {
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
