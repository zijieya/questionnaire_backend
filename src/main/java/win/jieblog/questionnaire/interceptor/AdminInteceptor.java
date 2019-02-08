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

/**
 * 管理员接口拦截
 */
public class AdminInteceptor extends HandlerInterceptorAdapter{
    private Logger logger= LoggerFactory.getLogger(AdminInteceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpServletRequest req = (HttpServletRequest)request;
        if (!req.getMethod().equals("DELETE"))
            return true;
        logger.info(req.getMethod());
        final String authHeader = req.getHeader("Authorization");
        JwtHelper jwtHelper=new JwtHelper();
        final String token = authHeader.substring(7);
        final Claims claims = jwtHelper.parseKey(token);
        String access= (String) claims.get("access");
        if (!access.equals("管理员")){
            logger.error(LogHelper.LogStatement("系统","管理员接口拦截","失败","没有权限"));
            throw new AuthorityException("没有权限", ErrorCode.FORBIDDEN_ACCESS.getCode());
        }
        return true;
    }
}
