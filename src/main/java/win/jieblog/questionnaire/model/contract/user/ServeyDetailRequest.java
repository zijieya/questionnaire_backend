package win.jieblog.questionnaire.model.contract.user;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;

/**
 * 问卷详情
 */
public class ServeyDetailRequest extends BaseRequest {
    @ApiModelProperty(value = "问卷序列号")
    private String surveyserialid;

    public String getSurveyserialid() {
        return surveyserialid;
    }

    public void setSurveyserialid(String surveyserialid) {
        this.surveyserialid = surveyserialid;
    }
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(surveyserialid))
            throw new NotFoundException("问卷序列号为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
    }
}
