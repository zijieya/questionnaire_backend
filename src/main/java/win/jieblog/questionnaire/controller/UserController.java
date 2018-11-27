package win.jieblog.questionnaire.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.FilteredEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.model.contract.user.GlobalSearchForUserRequest;
import win.jieblog.questionnaire.model.contract.user.GlobalSearchForUserResponse;
import win.jieblog.questionnaire.model.contract.user.ServeyDetailRequest;
import win.jieblog.questionnaire.model.contract.user.ServeyDetailResponse;
import win.jieblog.questionnaire.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest request;
    @ApiOperation(value = "问卷列表",notes = "问卷列表")
    @GetMapping(value = "/servey/{keyword}/{pageIndex}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public GlobalSearchForUserResponse globalSearchForUser(@PathVariable("keyword") String keyword, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) throws AuthorityException {
        GlobalSearchForUserRequest globalSearchForUserRequest=new GlobalSearchForUserRequest();
        globalSearchForUserRequest.setKeyword(keyword);
        globalSearchForUserRequest.setPageIndex(pageIndex);
        globalSearchForUserRequest.setPageSize(pageSize);
        return userService.globalSearchForUser(globalSearchForUserRequest);
    }
    @ApiOperation(value = "问卷详情",notes = "问卷详情")
    @GetMapping(value = "/serveydetail/{surveyserialid}")
    @ResponseStatus(HttpStatus.OK)
    public ServeyDetailResponse serveyDetail(@PathVariable("surveyserialid") String surveyserialid){
        ServeyDetailRequest request=new ServeyDetailRequest();
        request.setSurveyserialid(surveyserialid);
        return userService.serveyDetail(request);
    }
}
