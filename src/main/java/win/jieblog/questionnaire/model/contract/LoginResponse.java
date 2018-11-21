package win.jieblog.questionnaire.model.contract;

import io.swagger.annotations.ApiModelProperty;

public class LoginResponse {
    @ApiModelProperty("是否登录成功 true 成功 false 失败")
    private boolean isSuccessful;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
