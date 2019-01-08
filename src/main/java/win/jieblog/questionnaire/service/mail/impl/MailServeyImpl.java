package win.jieblog.questionnaire.service.mail.impl;

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
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.mail.ActiveCodeRequest;
import win.jieblog.questionnaire.model.contract.mail.ActiveCodeResponse;
import win.jieblog.questionnaire.model.contract.mail.SendMailRequest;
import win.jieblog.questionnaire.model.contract.mail.SendMailResponse;
import win.jieblog.questionnaire.model.entity.User;
import win.jieblog.questionnaire.service.mail.MailService;
import win.jieblog.questionnaire.utils.LogHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
public class MailServeyImpl implements MailService {
    private Logger logger= LoggerFactory.getLogger(MailServeyImpl.class);
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private Environment env;

    /**
     * 发送验证码
     * @param request
     * @return
     * @throws NotFoundException
     */
    @Override
    public SendMailResponse sendMail(SendMailRequest request) throws NotFoundException {
        // 验证邮箱是否存在 重用 getUserByEmailOrUsername
        List<User> list =userMapper.getUserByEmailOrUsername(request.getEmail(),null);
        if (list.size()==0){
            logger.error(LogHelper.LogStatement("系统","查找邮箱","失败","找不到该邮箱"));
            throw new NotFoundException("找不到该邮箱", ErrorCode.USER_NOT_FOUND.getCode());

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
    @Override
    public ActiveCodeResponse activeCode(ActiveCodeRequest request) throws NotFoundException {
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
}
