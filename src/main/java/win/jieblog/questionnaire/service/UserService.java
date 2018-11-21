package win.jieblog.questionnaire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.LoginRequest;
import win.jieblog.questionnaire.model.contract.LoginResponse;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    LoginResponse getLogin(HttpServletResponse resp, LoginRequest request) throws NotFoundException, JsonProcessingException;
}
