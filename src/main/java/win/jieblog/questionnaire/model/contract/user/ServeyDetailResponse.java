package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ServeyDetailResponse extends ServeyItem {
    @ApiModelProperty(value = "问题列表")
    List<QuestionItem> list;

    public List<QuestionItem> getList() {
        return list;
    }

    public void setList(List<QuestionItem> list) {
        this.list = list;
    }
}
