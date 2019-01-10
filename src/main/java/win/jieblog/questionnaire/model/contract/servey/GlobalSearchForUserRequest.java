package win.jieblog.questionnaire.model.contract.servey;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.utils.LogHelper;

public class GlobalSearchForUserRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(GlobalSearchForUserRequest.class);
    @ApiModelProperty(value = "用户序列号 当搜索类型为2或3时要用到")
    private String userserialid;
    @ApiModelProperty(value = "搜素类型 1:所有未过期的问卷 2:我的问卷 3:我回答的问卷")
    private int searchType;
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

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getUserserialid() {
        return userserialid;
    }

    public void setUserserialid(String userserialid) {
        this.userserialid = userserialid;
    }

    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(keyword)){
            logger.error(LogHelper.LogStatement("系统","搜索问卷","失败","关键字为空"));
            throw new NotFoundException("关键字为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (pageSize==0){
            logger.error(LogHelper.LogStatement("系统","搜索问卷","失败","页大小为空"));
            throw new NotFoundException("页大小为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (pageIndex==0){
            logger.error(LogHelper.LogStatement("系统","搜索问卷","失败","页号为空"));
            throw new NotFoundException("页号为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (searchType>3||searchType<1){
            logger.error(LogHelper.LogStatement("系统","搜索问卷","失败","搜索类型不合法"));
            throw new NotFoundException("搜索类型不合法",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
