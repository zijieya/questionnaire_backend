package win.jieblog.questionnaire.model.contract.common;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;

public class LoginRequest extends BaseRequest {
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
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(username))
            throw new NotFoundException("用户名为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        if (Strings.isNullOrEmpty(password))
            throw new NotFoundException("密码为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
    }
}
