package win.jieblog.questionnaire.model.contract.common;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.utils.LogHelper;

public class UploadAvatarRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(UploadAvatarRequest.class);
    @ApiModelProperty(value = "用户序列号")
    private String userserialid;
    @ApiModelProperty(value = "头像url")
    private String avatarUrl;
    @ApiModelProperty(value = "头像名称")
    private String avatarName;

    public String getUserserialid() {
        return userserialid;
    }

    public void setUserserialid(String userserialid) {
        this.userserialid = userserialid;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(userserialid)){
            logger.error(LogHelper.LogStatement("系统","上传头像","失败","用户序列号为空"));
            throw new NotFoundException("用户序列号为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (Strings.isNullOrEmpty(avatarUrl)){
            logger.error(LogHelper.LogStatement("系统","上传头像","失败","头像url为空"));
            throw new NotFoundException("头像url为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (Strings.isNullOrEmpty(avatarName)){
            logger.error(LogHelper.LogStatement("系统","上传头像","失败","头像名称为空"));
            throw new NotFoundException("头像名称为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
