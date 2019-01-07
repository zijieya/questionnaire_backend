package win.jieblog.questionnaire.model.contract.common;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.model.contract.BaseResponse;
import win.jieblog.questionnaire.utils.LogHelper;

public class SendMailRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(SendMailRequest.class);
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
        {
            logger.error(LogHelper.LogStatement("系统","发送验证码","失败","邮箱为空"));
            throw new NotFoundException("邮箱为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
