package win.jieblog.questionnaire.model.contract.common;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;

public class RegisterRequest extends BaseRequest {
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
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(email))
            throw new NotFoundException("邮箱为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        if (Strings.isNullOrEmpty(username))
            throw new NotFoundException("用户名为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        if (Strings.isNullOrEmpty(password))
            throw new NotFoundException("密码为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
    }
}
