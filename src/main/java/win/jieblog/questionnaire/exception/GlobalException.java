package win.jieblog.questionnaire.exception;

/**
 * 全局异常
 */
public class GlobalException extends Exception  {
    private int code;

    public GlobalException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
