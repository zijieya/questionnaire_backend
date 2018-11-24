package win.jieblog.questionnaire.enums;

public enum ErrorCode {
    USER_NOT_FOUND(40401),
    ACTIVECODE_NOT_FOUND(40402),
    EMPTY_TOKEN(40301),
    INVALID_TOKEN(40302),
    EXPIRE_TOKEN(40303),
    UPDATE_ERROR(50001);

    private int code;

    public int getCode() {
        return code;
    }

    ErrorCode(int code) {
        this.code = code;
    }
}
