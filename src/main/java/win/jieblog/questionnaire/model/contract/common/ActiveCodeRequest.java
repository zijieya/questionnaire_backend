package win.jieblog.questionnaire.model.contract.common;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.service.impl.CommonServiceImpl;
import win.jieblog.questionnaire.utils.LogHelper;

public class ActiveCodeRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(ActiveCodeRequest.class);
    @ApiModelProperty(value = "用户序列号")
    private String userserialid;
    @ApiModelProperty(value = "验证码")
    private int verificationCode;

    public String getUserserialid() {
        return userserialid;
    }

    public void setUserserialid(String userserialid) {
        this.userserialid = userserialid;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(userserialid)){
            logger.error(LogHelper.LogStatement("系统","验证码校验","失败","用户序列号为空"));
            throw new NotFoundException("用户序列号为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (verificationCode==0)
        {
            logger.error(LogHelper.LogStatement(userserialid,"验证码校验","失败","验证码为空"));
            throw new NotFoundException("验证码为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
