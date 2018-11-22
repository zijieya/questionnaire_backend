package win.jieblog.questionnaire.model.contract.common;

import io.swagger.annotations.ApiModelProperty;

public class LoginRequest {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String usermname) {
        this.username = usermname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
