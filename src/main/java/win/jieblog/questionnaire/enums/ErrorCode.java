package win.jieblog.questionnaire.enums;

public enum ErrorCode {
    USER_NOT_FOUND(40401);
    private int code;

    public int getCode() {
        return code;
    }

    ErrorCode(int code) {
        this.code = code;
    }
}
