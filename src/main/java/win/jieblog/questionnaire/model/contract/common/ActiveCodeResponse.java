package win.jieblog.questionnaire.model.contract.common;

import io.swagger.annotations.ApiModelProperty;

public class ActiveCodeResponse {
    @ApiModelProperty(value = "用户序列号")
    private String userserialid;
    @ApiModelProperty(value = "验证是否成功")
    private boolean isSuccessful;

    public String getUserserialid() {
        return userserialid;
    }

    public void setUserserialid(String userserialid) {
        this.userserialid = userserialid;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
