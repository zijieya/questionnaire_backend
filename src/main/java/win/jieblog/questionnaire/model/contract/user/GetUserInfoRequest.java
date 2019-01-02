package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

public class GetUserInfoRequest {
    @ApiModelProperty(value = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
