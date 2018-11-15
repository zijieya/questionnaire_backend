package win.jieblog.questionnaire.exception;

/**
 * 资源不存在的异常
 */
public class NotFoundException extends GlobalException {
    public NotFoundException(String message, int code) {
        super(message, code);
    }
}
