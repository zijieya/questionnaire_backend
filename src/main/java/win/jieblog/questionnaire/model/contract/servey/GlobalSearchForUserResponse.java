package win.jieblog.questionnaire.model.contract.servey;

import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.model.contract.BaseResponse;

import java.util.List;

public class GlobalSearchForUserResponse extends BaseResponse {
    @ApiModelProperty(value = "分页总数")
    private long total;
 private List<ServeyItem> list;

    public List<ServeyItem> getList() {
        return list;
    }

    public void setList(List<ServeyItem> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}