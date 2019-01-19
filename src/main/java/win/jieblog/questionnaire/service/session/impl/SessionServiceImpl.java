package win.jieblog.questionnaire.service.session.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import win.jieblog.questionnaire.dao.UserMapper;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.session.GetUserInfoRequest;
import win.jieblog.questionnaire.model.contract.session.GetUserInfoResponse;
import win.jieblog.questionnaire.model.contract.session.LoginRequest;
import win.jieblog.questionnaire.model.contract.session.LoginResponse;
import win.jieblog.questionnaire.model.entity.User;
import win.jieblog.questionnaire.service.session.SessionService;
import win.jieblog.questionnaire.utils.JwtHelper;
import win.jieblog.questionnaire.utils.LogHelper;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@Service
public class SessionServiceImpl implements SessionService {
    private final static long ttlTime=60*1000*60*24;//token的有效期为一天
    private static ObjectMapper MAPPER = new ObjectMapper();
    private Logger logger= LoggerFactory.getLogger(SessionServiceImpl.class);
    @Autowired
    @Qualifier("cacheRedis")
    private RedisTemplate template;
    @Autowired
    private UserMapper userMapper;
    @Override
    public LoginResponse getLogin(HttpServletResponse resp, LoginRequest request) throws NotFoundException, JsonProcessingException {
        request.validate();
        JwtHelper jwtHelper=new JwtHelper();
        User user=userMapper.selectUserByLogin(request.getUsername(),request.getPassword());
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
            logger.info(LogHelper.LogStatement(user.getUsername(),"登录","成功"));
        }
        return response;
    }
    @Cacheable(value = "response", key = "#root.targetClass + #request.token", unless = "#result eq null")
    @Override
    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) throws NotFoundException {
        GetUserInfoResponse response=new GetUserInfoResponse();
        String username= (String) template.opsForHash().get("tokentouser",request.getToken());
        logger.info(username);
        if (username==null){
            logger.error(LogHelper.LogStatement("token为"+request.getToken(),"通过token拉取用户信息","失败","token不存在"));
            throw new NotFoundException("token不存在",ErrorCode.EMPTY_TOKEN.getCode());
        }

        User user=userMapper.selectUserByEmailOrUsername("",username).get(0);
        response.setUsername(user.getUsername());
        response.setAccess(user.getRole());
        response.setAvatar(user.getAvatar());
        response.setUserId(user.getUserid());
        response.setUserSerialId(user.getUserserialid());
        response.setSuccessful(true);
        logger.info(LogHelper.LogStatement("token为"+request.getToken(),"通过token拉取用户信息","成功"));
        return response;
    }
}
