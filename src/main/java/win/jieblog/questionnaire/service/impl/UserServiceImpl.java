package win.jieblog.questionnaire.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import win.jieblog.questionnaire.dao.UserMapper;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.LoginRequest;
import win.jieblog.questionnaire.model.contract.LoginResponse;
import win.jieblog.questionnaire.model.contract.RegisterRequest;
import win.jieblog.questionnaire.model.contract.RegisterResponse;
import win.jieblog.questionnaire.model.entity.User;
import win.jieblog.questionnaire.service.UserService;
import win.jieblog.questionnaire.utils.JwtHelper;
import win.jieblog.questionnaire.utils.SerialsIdHelper;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private final static long ttlTime=60*1000*60;//token的有效期为一小时
    private static ObjectMapper MAPPER = new ObjectMapper();
    Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    @Autowired
    UserMapper userMapper;
    /**
     *
     * @param resp httpservlet
     * @param request 请求
     * @return
     * @throws NotFoundException
     * @throws JsonProcessingException
     */
    @Override
    public LoginResponse getLogin(HttpServletResponse resp,LoginRequest request) throws NotFoundException, JsonProcessingException {
        JwtHelper jwtHelper=new JwtHelper();
        User user=userMapper.getUserByLogin(request.getUsername(),request.getPassword());
        LoginResponse response=new LoginResponse();
        if (user==null){
            logger.error("用户名或密码错误");
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
            //存入 redis
            template.opsForHash().put("token",request.getUsername(),token);
            logger.info("存入"+user.getUsername()+"token到redis");
        }
        return response;
    }
    public RegisterResponse register(RegisterRequest request) throws NotFoundException{
        RegisterResponse response=new RegisterResponse();
        // contract转entity
        User user=new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        //校验用户名和邮箱是否已经存在
        List<User> list =userMapper.getUserByEmailOrUsername(request.getEmail(),request.getUsername());
        if (list.size()>0){
            logger.error("已存在"+request.getEmail()+"或者"+request.getUsername());
            throw new NotFoundException("已存在此用户名或邮箱",ErrorCode.USER_NOT_FOUND.getCode());
        }
        else
        {
            user.setPassword(request.getPassword());
            user.setUserserialid(SerialsIdHelper.getSerialsId());
            int count=userMapper.insertSelective(user);//影响的条数
            logger.info("增加用户"+user.getUsername());
            response.setSuccessful(true);
        }
        return response;
    }
}
