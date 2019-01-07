package win.jieblog.questionnaire.model.contract.common;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.model.contract.BaseResponse;

public class SendMailRequest extends BaseRequest {
    @ApiModelProperty(value = "邮箱")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(email))
            throw new NotFoundException("邮箱为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
    }
}
