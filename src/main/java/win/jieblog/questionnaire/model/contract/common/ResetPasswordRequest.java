package win.jieblog.questionnaire.model.contract.common;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.utils.LogHelper;

public class ResetPasswordRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(ResetPasswordRequest.class);
    @ApiModelProperty(value = "用户序列号")
    private String userserialid;
    @ApiModelProperty(value = "密码")
    private String password;

    public String getUserserialid() {
        return userserialid;
    }

    public void setUserserialid(String userserialid) {
        this.userserialid = userserialid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(userserialid))
        {
            logger.error(LogHelper.LogStatement("系统","重置密码","失败","用户序列号为空"));
            throw new NotFoundException("用户序列号为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (Strings.isNullOrEmpty(password))
        {
            logger.error(LogHelper.LogStatement(userserialid,"重置密码","失败","密码为空"));
            throw new NotFoundException("密码为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
