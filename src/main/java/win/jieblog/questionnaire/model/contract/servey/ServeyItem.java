package win.jieblog.questionnaire.model.contract.servey;

import io.swagger.annotations.ApiModelProperty;

/**
 * 问卷列表中的一个问卷
 */
public class ServeyItem {
    @ApiModelProperty(value = "问卷序列号")
    private String surveyserialid;
    @ApiModelProperty(value = "问卷创建者")
    private String creator;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "标签")
    private String tag;
    @ApiModelProperty(value = "作答次数")
    private int total;

    public String getSurveyserialid() {
        return surveyserialid;
    }

    public void setSurveyserialid(String surveyserialid) {
        this.surveyserialid = surveyserialid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
