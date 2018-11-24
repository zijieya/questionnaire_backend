package win.jieblog.questionnaire.exception;

/**
 * 数据库异常
 */
public class DataBaseErrorException extends GlobalException{
    public DataBaseErrorException(String message, int code) {
        super(message, code);
    }
}
