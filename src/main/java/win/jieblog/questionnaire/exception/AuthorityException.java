package win.jieblog.questionnaire.exception;

/**
 * 权限异常
 */
public class AuthorityException extends GlobalException {
    public AuthorityException(String message, int code) {
        super(message, code);
    }
}
