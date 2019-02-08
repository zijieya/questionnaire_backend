package win.jieblog.questionnaire.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.utils.JwtHelper;
import win.jieblog.questionnaire.utils.LogHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * todo：抛出异常信息
 */
//@WebFilter(urlPatterns = {"/v1/servey/*","/v1/mail/*","/v1/user"} )
//@Component
public class TokenFilter implements Filter  {
    private RedisTemplate template;
    private Logger logger= LoggerFactory.getLogger(TokenFilter.class);

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
            try {
                throw new AuthorityException("不存在token", ErrorCode.EMPTY_TOKEN.getCode());
            } catch (AuthorityException e) {
                e.printStackTrace();
            }
            logger.error(LogHelper.LogStatement("系统","token过滤","失败","不存在token"));
        }
        else
        {
            if(!authHeader.startsWith("Bearer ")){
                try {
                    throw new AuthorityException("token不合法",ErrorCode.INVALID_TOKEN.getCode());
                } catch (AuthorityException e) {
                    logger.error(LogHelper.LogStatement("系统","token过滤","失败","token不合法"));
                }
            }
            //进行校验
            else
            {
                JwtHelper jwtHelper=new JwtHelper();
                final String token = authHeader.substring(7);
                final Claims claims = jwtHelper.parseKey(token);
                //过期
                if(claims.getExpiration().before(new Date(System.currentTimeMillis()))){
                    try {
                        throw new AuthorityException("token过期",ErrorCode.EXPIRE_TOKEN.getCode());
                    } catch (AuthorityException e) {
                        logger.error(LogHelper.LogStatement("系统","token过滤","失败","不存在token"));
                    }
                }

                ObjectMapper om = new ObjectMapper();
                JsonNode tree = om.readTree(claims.getSubject());
                String username=tree.findValues("username").get(0).toString().replace("\"","");
                // 和redis中比较
                String tokenInRedis=(String) template.opsForHash().get("token",username);
                logger.info(LogHelper.LogStatement("系统","从redis中取出"+username+"的token","成功"));
                if(!tokenInRedis.equals(token)){
                    try {
                        throw new AuthorityException("token不合法",ErrorCode.INVALID_TOKEN.getCode());
                    } catch (AuthorityException e) {
                        logger.error(LogHelper.LogStatement("系统","token过滤","失败","token不合法"));
                    }
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
