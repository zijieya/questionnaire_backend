package win.jieblog.questionnaire.service.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.session.GetUserInfoRequest;
import win.jieblog.questionnaire.model.contract.session.GetUserInfoResponse;
import win.jieblog.questionnaire.model.contract.session.LoginRequest;
import win.jieblog.questionnaire.model.contract.session.LoginResponse;

import javax.servlet.http.HttpServletResponse;

public interface SessionService {
    LoginResponse getLogin(HttpServletResponse resp, LoginRequest request) throws NotFoundException, JsonProcessingException;
    GetUserInfoResponse getUserInfo(GetUserInfoRequest request) throws NotFoundException;
}
