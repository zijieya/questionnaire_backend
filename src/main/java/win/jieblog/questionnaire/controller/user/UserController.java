package win.jieblog.questionnaire.controller.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.session.RegisterRequest;
import win.jieblog.questionnaire.model.contract.session.RegisterResponse;
import win.jieblog.questionnaire.model.contract.user.ResetPasswordRequest;
import win.jieblog.questionnaire.model.contract.user.ResetPasswordResponse;
import win.jieblog.questionnaire.model.contract.user.UploadAvatarRequest;
import win.jieblog.questionnaire.model.contract.user.UploadAvatarResponse;
import win.jieblog.questionnaire.service.user.UserService;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "注册",notes = "注册用户,并进行相关校验")
    @PostMapping("/v1/user")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse regitser(@RequestBody RegisterRequest request) throws NotFoundException {
        return userService.register(request);
    }
    @ApiOperation(value = "重置密码",notes = "重置密码")
    @PutMapping("/v1/user/password")
    @ResponseStatus(HttpStatus.OK)
    public ResetPasswordResponse resetPassword(@RequestBody ResetPasswordRequest request) throws DataBaseErrorException {
        return userService.resetPassword(request);
    }
    @ApiOperation(value = "上传头像",notes = "上传头像")
    @PutMapping("/v1/user/avatar")
    @ResponseStatus(HttpStatus.OK)
    public UploadAvatarResponse uploadAvatar(UploadAvatarRequest request) throws NotFoundException, DataBaseErrorException, IOException {
        return userService.uploadAvatar(request);
    }
}
