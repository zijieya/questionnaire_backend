package win.jieblog.questionnaire.model.contract.user;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;

import java.util.List;
import java.util.Map;

public class SubmitServeyRequest extends BaseRequest {
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
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(surveyserialid))
            throw new NotFoundException("用户序列号为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        if (Strings.isNullOrEmpty(answererserialId))
            throw new NotFoundException("回答者序列号为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        if (Strings.isNullOrEmpty(answer.toString()))
            throw new NotFoundException("答案为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
    }
}

