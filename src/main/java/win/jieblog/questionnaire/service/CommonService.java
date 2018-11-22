package win.jieblog.questionnaire.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.common.*;

import javax.servlet.http.HttpServletResponse;

public interface CommonService {
    LoginResponse getLogin(HttpServletResponse resp, LoginRequest request) throws NotFoundException, JsonProcessingException;
    RegisterResponse register(RegisterRequest request) throws NotFoundException;
    SendMailResponse sendMail(SendMailRequest request) throws NotFoundException;
}
