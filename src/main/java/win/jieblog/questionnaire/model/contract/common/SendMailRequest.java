package win.jieblog.questionnaire.model.contract.common;

import io.swagger.annotations.ApiModelProperty;

public class SendMailRequest {
    @ApiModelProperty(value = "邮箱")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
