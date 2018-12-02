package win.jieblog.questionnaire.model.contract.user;

import win.jieblog.questionnaire.model.contract.BaseResponse;

import java.util.List;

public class GlobalSearchForUserResponse extends BaseResponse {
 private List<ServeyItem> list;

    public List<ServeyItem> getList() {
        return list;
    }

    public void setList(List<ServeyItem> list) {
        this.list = list;
    }
}