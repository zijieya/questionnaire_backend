package win.jieblog.questionnaire.model.contract.mail;

import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.model.contract.BaseResponse;

public class ActiveCodeResponse extends BaseResponse {
    @ApiModelProperty(value = "用户序列号")
    private String userserialid;
    public String getUserserialid() {
        return userserialid;
    }

    public void setUserserialid(String userserialid) {
        this.userserialid = userserialid;
    }

}
