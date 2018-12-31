package win.jieblog.questionnaire.model.contract;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BaseResponse {
    @ApiModelProperty(value = "响应是否成功")
    private boolean isSuccessful;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @ApiModelProperty(value = "响应时间")
    private Date serverDate;
    @ApiModelProperty(value = "业务响应代码",notes = "当不抛出异常时则表明请求正常" )
    private int code=200200;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public Date getServerDate() {
        return new Date();
    }

    public int getCode() {
        return code;
    }
}
