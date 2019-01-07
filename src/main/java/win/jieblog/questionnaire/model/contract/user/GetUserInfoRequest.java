package win.jieblog.questionnaire.model.contract.user;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;

public class GetUserInfoRequest extends BaseRequest {
    @ApiModelProperty(value = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(token))
            throw new NotFoundException("token为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
    }
}
