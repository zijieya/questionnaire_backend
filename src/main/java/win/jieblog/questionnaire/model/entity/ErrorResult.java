package win.jieblog.questionnaire.model.entity;

public class ErrorResult {
    /**
     * 错误内容
     */
    private String error;
    /**
     * 自定义错误码
     */
    private int code;

    public ErrorResult(String error, int code) {
        this.error = error;
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
