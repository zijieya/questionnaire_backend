package win.jieblog.questionnaire.controller.serveyresult;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import win.jieblog.questionnaire.model.contract.serveyresult.SubmitServeyRequest;
import win.jieblog.questionnaire.model.contract.serveyresult.SubmitServeyResponse;
import win.jieblog.questionnaire.service.serveyresult.ServeyResultService;

@RestController
public class ServeyResultController {
    @Autowired
    private ServeyResultService serveyResultService;
    @ApiModelProperty(value = "提交问卷")
    @PostMapping(value = "/v1/servey/result")
    @ResponseStatus(HttpStatus.CREATED)
    public SubmitServeyResponse submitServey(SubmitServeyRequest request){
        return serveyResultService.submitServey(request);
    }
}
