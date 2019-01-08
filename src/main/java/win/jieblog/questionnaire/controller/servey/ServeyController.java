package win.jieblog.questionnaire.controller.servey;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.model.contract.servey.*;
import win.jieblog.questionnaire.service.servey.ServeyService;

@RestController
public class ServeyController {
    @Autowired
    private ServeyService serveyService;
    @ApiOperation(value = "问卷列表",notes = "问卷列表")
    @GetMapping(value = "/v1/servey/{keyword}/{pageIndex}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public GlobalSearchForUserResponse globalSearchForUser(@PathVariable("keyword") String keyword, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) throws AuthorityException {
        GlobalSearchForUserRequest globalSearchForUserRequest=new GlobalSearchForUserRequest();
        globalSearchForUserRequest.setKeyword(keyword);
        globalSearchForUserRequest.setPageIndex(pageIndex);
        globalSearchForUserRequest.setPageSize(pageSize);
        return serveyService.globalSearchForUser(globalSearchForUserRequest);
    }
    @ApiOperation(value = "问卷详情",notes = "问卷详情")
    @GetMapping(value = "/v1/servey/{surveyserialid}")
    @ResponseStatus(HttpStatus.OK)
    public ServeyDetailResponse serveyDetail(@PathVariable("surveyserialid") String surveyserialid){
        ServeyDetailRequest request=new ServeyDetailRequest();
        request.setSurveyserialid(surveyserialid);
        return serveyService.serveyDetail(request);
    }
    @ApiModelProperty(value = "生成问卷")
    @PostMapping(value = "/v1/servey")
    @ResponseStatus(HttpStatus.CREATED)
    public GenerateServeyResponse generateServey(@RequestBody GenerateServeyRequest request) throws DataBaseErrorException {
        return serveyService.generateServey(request);
    }
}
