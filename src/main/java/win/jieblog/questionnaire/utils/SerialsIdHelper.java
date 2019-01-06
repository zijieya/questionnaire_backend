package win.jieblog.questionnaire.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * SerialsId生成类
 */
@Component
public class SerialsIdHelper {
    public static String getSerialsId(){
        return UUID.randomUUID().toString();
    }
}
