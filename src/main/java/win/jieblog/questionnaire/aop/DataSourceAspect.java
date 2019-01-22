package win.jieblog.questionnaire.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import win.jieblog.questionnaire.configuration.DatabaseContextHolder;
import win.jieblog.questionnaire.enums.DatabaseType;

@Aspect
@Component
public class DataSourceAspect {
    @Pointcut("execution(* win.jieblog.questionnaire.dao.*.select*(..))")
    public void resource(){}
    @Before(value ="resource()")
    public void before(JoinPoint joinPoint){
        DatabaseContextHolder.setDatabaseType(DatabaseType.READ);
    }
    @After(value = "resource()")
    public void after(JoinPoint joinPoint){
        DatabaseContextHolder.setDatabaseType(DatabaseType.WRITE);
    }
}
