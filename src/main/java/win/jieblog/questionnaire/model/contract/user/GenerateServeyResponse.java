package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

public class GenerateServeyResponse {
    @ApiModelProperty(value = "true 成功 false 失败")
    private boolean isSuccessful;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
