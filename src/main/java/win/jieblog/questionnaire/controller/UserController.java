package win.jieblog.questionnaire.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.LoginRequest;
import win.jieblog.questionnaire.model.contract.LoginResponse;
import win.jieblog.questionnaire.service.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户操作
 */
@RestController
public class UserController {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserService userService;
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
      return userService.getLogin(resp,request);
    }
}

