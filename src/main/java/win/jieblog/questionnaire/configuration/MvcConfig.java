package win.jieblog.questionnaire.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import win.jieblog.questionnaire.interceptor.AdminInteceptor;
import win.jieblog.questionnaire.interceptor.LoginInteceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns=new ArrayList<>();
        patterns.add("/v1/servey/*");
        patterns.add("/v1/mail/*");
        patterns.add("/v1/user");
        registry.addInterceptor(new LoginInteceptor()).addPathPatterns(patterns).order(1);
        registry.addInterceptor(new AdminInteceptor()).addPathPatterns("/v1/user").order(2);
    }
}
