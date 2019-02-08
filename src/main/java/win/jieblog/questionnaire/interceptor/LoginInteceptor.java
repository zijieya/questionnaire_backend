package win.jieblog.questionnaire.interceptor;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.utils.JwtHelper;
import win.jieblog.questionnaire.utils.LogHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LoginInteceptor extends HandlerInterceptorAdapter {
    private Logger logger= LoggerFactory.getLogger(LoginInteceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpServletRequest req = (HttpServletRequest)request;
        final String authHeader = req.getHeader("Authorization");
        if (authHeader == null) {
            logger.error(LogHelper.LogStatement("系统","登录拦截","失败","不存在token"));
            throw new AuthorityException("不存在token", ErrorCode.EMPTY_TOKEN.getCode());
            }
        else
        {
            if(!authHeader.startsWith("Bearer ")){
                logger.error(LogHelper.LogStatement("系统","登录拦截","失败","token不合法"));
                throw new AuthorityException("token不合法",ErrorCode.INVALID_TOKEN.getCode());
                }
            //进行校验
            else
            {
                JwtHelper jwtHelper=new JwtHelper();
                final String token = authHeader.substring(7);
                final Claims claims = jwtHelper.parseKey(token);
                //过期
                if(claims.getExpiration().before(new Date(System.currentTimeMillis()))){
                    logger.error(LogHelper.LogStatement("系统","登录拦截","失败","不存在token"));
                    throw new AuthorityException("token过期",ErrorCode.EXPIRE_TOKEN.getCode());
                    }
                return true;
            }
        }
    }
}
