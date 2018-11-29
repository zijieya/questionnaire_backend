package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 生成问卷
 */
public class GenerateServeyRequest {
    @ApiModelProperty(value = "创建者序列号")
    private String creatorserialId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "标签")
    private String tag;
    @ApiModelProperty(value = "备注")
    private String remark;
    private List<QuestionInGeneration> list;

    public String getCreatorserialId() {
        return creatorserialId;
    }

    public void setCreatorserialId(String creatorserialId) {
        this.creatorserialId = creatorserialId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<QuestionInGeneration> getList() {
        return list;
    }

    public void setList(List<QuestionInGeneration> list) {
        this.list = list;
    }
}

