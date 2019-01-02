package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import win.jieblog.questionnaire.model.contract.BaseResponse;

public class GetUserInfoResponse extends BaseResponse {
    @ApiModelProperty(value = "用户编号")
    private int userId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户头像url")
    private String avatar;
    @ApiModelProperty(value = "用户权限",notes = "前端模板为此字段")
    private String access;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
