package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * 问卷详情
 */
public class ServeyDetailRequest {
    @ApiModelProperty(value = "问卷序列号")
    private String surveyserialid;

    public String getSurveyserialid() {
        return surveyserialid;
    }

    public void setSurveyserialid(String surveyserialid) {
        this.surveyserialid = surveyserialid;
    }
}
