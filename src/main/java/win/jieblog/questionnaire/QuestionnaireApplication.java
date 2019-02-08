package win.jieblog.questionnaire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "win.jieblog.questionnaire")
@MapperScan(value = "win.jieblog.questionnaire.dao")
@ServletComponentScan(value = "win.jieblog.questionnaire.filter")
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableJms
public class QuestionnaireApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApplication.class, args);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
   }
}
