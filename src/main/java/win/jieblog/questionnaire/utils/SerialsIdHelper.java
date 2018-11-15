package win.jieblog.questionnaire.utils;

import java.util.UUID;

/**
 * SerialsId生成类
 */
public class SerialsIdHelper {
    public static String getSerialsId(){
        return UUID.randomUUID().toString();
    }
}
