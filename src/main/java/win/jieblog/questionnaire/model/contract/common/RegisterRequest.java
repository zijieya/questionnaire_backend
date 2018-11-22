package win.jieblog.questionnaire.model.contract.common;

import io.swagger.annotations.ApiModelProperty;

public class RegisterRequest {
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
