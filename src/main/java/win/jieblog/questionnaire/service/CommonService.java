package win.jieblog.questionnaire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.common.*;
import win.jieblog.questionnaire.model.contract.user.GetUserInfoRequest;
import win.jieblog.questionnaire.model.contract.user.GetUserInfoResponse;

import javax.servlet.http.HttpServletResponse;

public interface CommonService {
    LoginResponse getLogin(HttpServletResponse resp, LoginRequest request) throws NotFoundException, JsonProcessingException;
    RegisterResponse register(RegisterRequest request) throws NotFoundException;
    SendMailResponse sendMail(SendMailRequest request) throws NotFoundException;
    ActiveCodeResponse activeCode(ActiveCodeRequest request) throws NotFoundException;
    ResetPasswordResponse resetPassword(ResetPasswordRequest request) throws DataBaseErrorException;
    GetUserInfoResponse getUserInfo(GetUserInfoRequest request) throws NotFoundException;

}
