package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

public class SubmitServeyRequest {
    @ApiModelProperty(value = "问卷序列号")
    private String surveyserialid;
    @ApiModelProperty(value = "回答者序列号")
    private String answererserialId;
    @ApiModelProperty(value = "答案")
    private Map<String,String> answer;
    @ApiModelProperty(value = "用户标签")
    private String remark;
    @ApiModelProperty(value = "提交类型")
    private int submitType=0;
    @ApiModelProperty(value = "答案列表")
    private List<AnswerInSubmitServey> list;

    public String getSurveyserialid() {
        return surveyserialid;
    }

    public void setSurveyserialid(String surveyserialid) {
        this.surveyserialid = surveyserialid;
    }

    public String getAnswererserialId() {
        return answererserialId;
    }

    public void setAnswererserialId(String answererserialId) {
        this.answererserialId = answererserialId;
    }

    public Map<String, String> getAnswer() {
        return answer;
    }

    public void setAnswer(Map<String, String> answer) {
        this.answer = answer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSubmitType() {
        return submitType;
    }

    public void setSubmitType(int submitType) {
        this.submitType = submitType;
    }

    public List<AnswerInSubmitServey> getList() {
        return list;
    }

    public void setList(List<AnswerInSubmitServey> list) {
        this.list = list;
    }
}
