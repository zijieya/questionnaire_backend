package win.jieblog.questionnaire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.common.*;
import win.jieblog.questionnaire.model.contract.user.GetUserInfoRequest;
import win.jieblog.questionnaire.model.contract.user.GetUserInfoResponse;
import win.jieblog.questionnaire.service.CommonService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 存放一些公共操作
 */
@RestController
public class CommonController {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    CommonService commonService;
    @ApiOperation(value = "登录",notes = "登录获取token")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse getUserByLogin(HttpServletResponse resp, @RequestBody LoginRequest request) throws NotFoundException, JsonProcessingException {
      return commonService.getLogin(resp,request);
    }
    @ApiOperation(value = "注册",notes = "注册用户,并进行相关校验")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse regitser(@RequestBody RegisterRequest request) throws NotFoundException {
        return commonService.register(request);
    }
    @ApiOperation(value = "发送验证码",notes = "发送验证码")
    @PostMapping("/sendmail")
    @ResponseStatus(HttpStatus.OK)
    public SendMailResponse sendMail(@RequestBody SendMailRequest request) throws NotFoundException{
        return commonService.sendMail(request);
    }
    @ApiOperation(value = "验证码校验",notes = "验证码校验")
    @PostMapping("/activecode")
    @ResponseStatus(HttpStatus.OK)
    public ActiveCodeResponse activeCode(@RequestBody ActiveCodeRequest request) throws NotFoundException{
        return commonService.activeCode(request);
    }
    @ApiOperation(value = "重置密码",notes = "重置密码")
    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public ResetPasswordResponse resetPassword(@RequestBody ResetPasswordRequest request) throws DataBaseErrorException {
        return commonService.resetPassword(request);
    }
    @ApiOperation(value = "通过token拉取用户信息",notes = "通过token拉取用户信息,有特殊字符只能用post")
    @PostMapping("/userinfo")
    @ResponseStatus(HttpStatus.OK)
    public GetUserInfoResponse getUserInfo(@RequestBody GetUserInfoRequest request) throws NotFoundException {
        return  commonService.getUserInfo(request);
    }
}

