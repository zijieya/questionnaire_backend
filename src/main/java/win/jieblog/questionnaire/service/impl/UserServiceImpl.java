package win.jieblog.questionnaire.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import win.jieblog.questionnaire.dao.UserMapper;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.LoginRequest;
import win.jieblog.questionnaire.model.contract.LoginResponse;
import win.jieblog.questionnaire.model.entity.User;
import win.jieblog.questionnaire.service.UserService;
import win.jieblog.questionnaire.utils.JwtHelper;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final static long ttlTime=60*1000*60;//token的有效期为一小时
    private static ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    @Autowired
    UserMapper userMapper;
    @Override
    public LoginResponse getLogin(HttpServletResponse resp,LoginRequest request) throws NotFoundException, JsonProcessingException {
        JwtHelper jwtHelper=new JwtHelper();
        User user=userMapper.getUserByLogin(request.getUsername(),request.getPassword());
        LoginResponse response=new LoginResponse();
        if (user==null)
            throw new NotFoundException("用户名或密码错误", ErrorCode.USER_NOT_FOUND.getCode());
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
        }
        return response;
    }
}
