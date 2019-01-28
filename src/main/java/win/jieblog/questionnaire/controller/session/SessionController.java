package win.jieblog.questionnaire.controller.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.session.*;
import win.jieblog.questionnaire.service.session.SessionService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @ApiOperation(value = "登录 创建新的会话",notes = "登录获取token")
    @PostMapping("/v1/session")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse getUserByLogin(HttpServletResponse resp, @RequestBody LoginRequest request) throws NotFoundException, JsonProcessingException {
        return sessionService.getLogin(resp,request);
    }
    @ApiOperation(value = "通过token拉取用户信息",notes = "通过token拉取用户信息,有特殊字符只能用post")
    @PostMapping("/v1/token/user")
    @ResponseStatus(HttpStatus.OK)
    public GetUserInfoResponse getUserInfo(@RequestBody GetUserInfoRequest request) throws NotFoundException {
        return sessionService.getUserInfo(request);
    }
    @ApiOperation(value = "注销",notes = "清除token")
    @DeleteMapping("/v1/session")
    @ResponseStatus(HttpStatus.OK)
    public LogoutResponse logout(@RequestBody LogoutRequest request) throws NotFoundException, JsonProcessingException {
        return sessionService.logout(request);
    }
}
