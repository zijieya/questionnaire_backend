package win.jieblog.questionnaire.model.contract.user;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;

public class GlobalSearchForUserRequest extends BaseRequest {
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
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(keyword))
            throw new NotFoundException("关键字为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        if (pageSize==0)
            throw new NotFoundException("页大小为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        if (pageIndex==0)
            throw new NotFoundException("页大小为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
    }
}
