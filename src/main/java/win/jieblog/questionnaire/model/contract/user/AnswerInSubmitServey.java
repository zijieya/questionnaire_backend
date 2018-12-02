package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * 提交问卷中的题目答案
 */
public class AnswerInSubmitServey {
    @ApiModelProperty(value = "问题编号")
    private long questionid;
    @ApiModelProperty(value = "答案")
    private String Answer;

    public long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(long questionid) {
        this.questionid = questionid;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
