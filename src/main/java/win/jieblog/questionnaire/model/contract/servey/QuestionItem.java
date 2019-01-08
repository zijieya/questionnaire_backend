package win.jieblog.questionnaire.model.contract.servey;

import io.swagger.annotations.ApiModelProperty;

public class QuestionItem {
    @ApiModelProperty(value = "问题")
    private String questionname;
    @ApiModelProperty(value = "答案A")
    private String answerA;
    @ApiModelProperty(value = "答案B")
    private String answerB;
    @ApiModelProperty(value = "答案C")
    private String answerC;
    @ApiModelProperty(value = "答案D")
    private String answerD;
    @ApiModelProperty(value = "答案E")
    private String answerE;
    @ApiModelProperty(value = "答案F")
    private String answerF;
    @ApiModelProperty(value = "答案G")
    private String answerG;

    public String getQuestionname() {
        return questionname;
    }

    public void setQuestionname(String questionname) {
        this.questionname = questionname;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswerE() {
        return answerE;
    }

    public void setAnswerE(String answerE) {
        this.answerE = answerE;
    }

    public String getAnswerF() {
        return answerF;
    }

    public void setAnswerF(String answerF) {
        this.answerF = answerF;
    }

    public String getAnswerG() {
        return answerG;
    }

    public void setAnswerG(String answerG) {
        this.answerG = answerG;
    }
}
