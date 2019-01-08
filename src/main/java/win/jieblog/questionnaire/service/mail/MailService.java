package win.jieblog.questionnaire.service.mail;

import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.mail.ActiveCodeRequest;
import win.jieblog.questionnaire.model.contract.mail.ActiveCodeResponse;
import win.jieblog.questionnaire.model.contract.mail.SendMailRequest;
import win.jieblog.questionnaire.model.contract.mail.SendMailResponse;

public interface MailService {
    SendMailResponse sendMail(SendMailRequest request) throws NotFoundException;
    ActiveCodeResponse activeCode(ActiveCodeRequest request) throws NotFoundException;
}
