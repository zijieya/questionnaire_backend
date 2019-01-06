package win.jieblog.questionnaire.enums;

public enum ErrorCode {
    USER_NOT_FOUND(40401),
    ACTIVECODE_NOT_FOUND(40402),
    EMPTY_TOKEN(40301),
    INVALID_TOKEN(40302),
    EXPIRE_TOKEN(40303),
    ERROR_TOKEN(40304),
    UPDATE_ERROR(50001),
    INSERT_ERROR(50002),
    URL_NOT_FOUND(40403),
    RESOURCENAME_NOT_FOUND(40404);
    private int code;

    public int getCode() {
        return code;
    }

    ErrorCode(int code) {
        this.code = code;
    }
}
