package win.jieblog.questionnaire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.LoginRequest;
import win.jieblog.questionnaire.model.contract.LoginResponse;
import win.jieblog.questionnaire.model.contract.RegisterRequest;
import win.jieblog.questionnaire.model.contract.RegisterResponse;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    LoginResponse getLogin(HttpServletResponse resp, LoginRequest request) throws NotFoundException, JsonProcessingException;
    RegisterResponse register(RegisterRequest request) throws NotFoundException;
}
