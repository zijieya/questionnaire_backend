package win.jieblog.questionnaire.enums;

public enum ErrorCode {
    USER_NOT_FOUND(40401),
    EMPTY_TOKEN(40301),
    INVALID_TOKEN(40302),
    EXPIRE_TOKEN(40303);
    private int code;

    public int getCode() {
        return code;
    }

    ErrorCode(int code) {
        this.code = code;
    }
}
