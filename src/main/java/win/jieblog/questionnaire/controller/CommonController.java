package win.jieblog.questionnaire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.common.*;
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
    /**
     * 登录
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "登录",notes = "登录获取token")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse getUserByLogin(HttpServletResponse resp, @RequestBody LoginRequest request) throws NotFoundException, JsonProcessingException {
      return commonService.getLogin(resp,request);
    }

    /**
     * 注册
     * @param request
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "注册",notes = "注册用户,并进行相关校验")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse regitser(RegisterRequest request) throws NotFoundException {
        return commonService.register(request);
    }
    @ApiOperation(value = "发送验证码",notes = "发送验证码")
    @PostMapping("/sendmail")
    @ResponseStatus(HttpStatus.OK)
    public SendMailResponse sendMail(SendMailRequest request) throws NotFoundException{
        return commonService.sendMail(request);
    }
    @ApiOperation(value = "验证码校验",notes = "验证码校验")
    @PostMapping("/activecode")
    @ResponseStatus(HttpStatus.OK)
    public ActiveCodeResponse activeCode(ActiveCodeRequest request) throws NotFoundException{
        return commonService.activeCode(request);
    }
    @ApiOperation(value = "重置密码",notes = "重置密码")
    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) throws DataBaseErrorException {
        return commonService.resetPassword(request);
    }
}

