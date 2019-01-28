package win.jieblog.questionnaire.model.contract.session;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.utils.LogHelper;

public class LogoutRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(LogoutRequest.class);
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
        if (Strings.isNullOrEmpty(token)){
            logger.error(LogHelper.LogStatement("token为"+token+"的用户","通过token获取用户信息","失败","token为空"));
            throw new NotFoundException("token为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
