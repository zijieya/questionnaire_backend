package win.jieblog.questionnaire.model.contract.session;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.utils.LogHelper;

public class LoginRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(LoginRequest.class);
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
        if (Strings.isNullOrEmpty(username)){
            logger.error(LogHelper.LogStatement("系统","登录","失败","用户名为空"));
            throw new NotFoundException("用户名为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (Strings.isNullOrEmpty(password)){
            logger.error(LogHelper.LogStatement(username,"登录","失败","密码为空"));
            throw new NotFoundException("密码为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
