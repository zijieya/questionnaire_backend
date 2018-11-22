package win.jieblog.questionnaire.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.utils.JwtHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * todo：抛出异常信息
 */
@WebFilter(urlPatterns = "/user/*")
public class LoginFilter implements Filter  {
    private LoggerFactory loggerFactory;
    private RedisTemplate template;
    @Autowired
    public void setTemplate(@Qualifier("redisTemplate") RedisTemplate template) {
        this.template = template;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest)request;
        final String authHeader = req.getHeader("Authorization");
        if (authHeader == null) {
            req.setAttribute("message","不存在token");
            req.setAttribute("errorCode",ErrorCode.EMPTY_TOKEN.getCode());
            chain.doFilter(request, response);
        }
        else
        {
            if(!authHeader.startsWith("Bearer ")){
                req.setAttribute("message","token不合法");
                req.setAttribute("errorCode",ErrorCode.INVALID_TOKEN.getCode());
                chain.doFilter(request, response);
            }
            //进行校验
            else
            {
                JwtHelper jwtHelper=new JwtHelper();
                final String token = authHeader.substring(7);
                final Claims claims = jwtHelper.parseKey(token);
                //过期
                if(claims.getExpiration().before(new Date(System.currentTimeMillis()))){
                    req.setAttribute("message","token过期");
                    req.setAttribute("errorCode",ErrorCode.EXPIRE_TOKEN.getCode());
                    chain.doFilter(request, response);
                }

                ObjectMapper om = new ObjectMapper();
                JsonNode tree = om.readTree(claims.getSubject());
                String username=tree.findValues("username").toString();
                // 和redis中比较
                String tokenInRedis=(String) template.opsForHash().get("token",username);
                if(!tokenInRedis.equals(token)){
                    req.setAttribute("message","token不合法");
                    req.setAttribute("errorCode",ErrorCode.INVALID_TOKEN.getCode());
                    chain.doFilter(request, response);
                }else
                {
                    chain.doFilter(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
