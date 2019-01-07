package win.jieblog.questionnaire.model.contract.user;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.model.contract.common.SendMailRequest;
import win.jieblog.questionnaire.utils.LogHelper;

import java.util.List;

/**
 * 生成问卷
 */
public class GenerateServeyRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(GenerateServeyRequest.class);
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
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(creatorserialId))
        {
            logger.error(LogHelper.LogStatement("系统","生成问卷","失败","创建者序列号为空"));
            throw new NotFoundException("创建者序列号为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (Strings.isNullOrEmpty(title)){
            logger.error(LogHelper.LogStatement(creatorserialId,"生成问卷","失败","标题为空"));
            throw new NotFoundException("标题为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
        if (Strings.isNullOrEmpty(list.toString())){
            logger.error(LogHelper.LogStatement(creatorserialId,"生成问卷","失败","问卷内容为空"));
            throw new NotFoundException("问卷内容为空",ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}

