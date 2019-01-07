package win.jieblog.questionnaire.model.contract.user;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.BaseRequest;
import win.jieblog.questionnaire.utils.LogHelper;

/**
 * 问卷详情
 */
public class ServeyDetailRequest extends BaseRequest {
    private Logger logger= LoggerFactory.getLogger(ServeyDetailRequest.class);
    @ApiModelProperty(value = "问卷序列号")
    private String surveyserialid;

    public String getSurveyserialid() {
        return surveyserialid;
    }

    public void setSurveyserialid(String surveyserialid) {
        this.surveyserialid = surveyserialid;
    }
    @Override
    public void validate() throws NotFoundException {
        if (Strings.isNullOrEmpty(surveyserialid)){
            logger.error(LogHelper.LogStatement("系统","问卷详情","失败","问卷序列号为空"));
            throw new NotFoundException("问卷序列号为空", ErrorCode.RESOURCENAME_NOT_FOUND.getCode());
        }
    }
}
