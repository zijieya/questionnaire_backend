package win.jieblog.questionnaire.model.contract.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.model.contract.BaseResponse;

public class SendMailResponse extends BaseResponse {
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "用户序列号")
    private String userserialid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserserialid() {
        return userserialid;
    }

    public void setUserserialid(String userserialid) {
        this.userserialid = userserialid;
    }
}
