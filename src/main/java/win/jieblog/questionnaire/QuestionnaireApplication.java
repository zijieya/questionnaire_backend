package win.jieblog.questionnaire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "win.jieblog.questionnaire")
@MapperScan(value = "win.jieblog.questionnaire.dao")
@ServletComponentScan(value = "win.jieblog.questionnaire.filter")
public class QuestionnaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApplication.class, args);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
