package win.jieblog.questionnaire.model.contract.user;

import io.swagger.annotations.ApiModelProperty;

public class GlobalSearchForUserRequest {
    @ApiModelProperty(value = "搜索关键字 支持标签，标题模糊搜索")
    private String keyword;
    @ApiModelProperty(value = "页大小")
    private int pageSize;
    @ApiModelProperty(value = "页大小")
    private int pageIndex;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
