package win.jieblog.questionnaire.model.contract.common;

import io.swagger.annotations.ApiModelProperty;

public class SendMailResponse {
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "验证码")
    private int verificationCode;
    @ApiModelProperty(value = "是否成功 true 发送成功 false 发送失败")
    private boolean isSuccessful;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
