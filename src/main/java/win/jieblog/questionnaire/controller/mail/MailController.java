package win.jieblog.questionnaire.controller.mail;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.mail.ActiveCodeRequest;
import win.jieblog.questionnaire.model.contract.mail.ActiveCodeResponse;
import win.jieblog.questionnaire.model.contract.mail.SendMailRequest;
import win.jieblog.questionnaire.model.contract.mail.SendMailResponse;
import win.jieblog.questionnaire.service.mail.MailService;

/**
 * 邮件接口
 */
@RestController
public class MailController {
    @Autowired
    private MailService mailService;
    @ApiOperation(value = "发送验证码",notes = "发送验证码")
    @PostMapping("/v1/mail/code")
    @ResponseStatus(HttpStatus.CREATED)
    public SendMailResponse sendMail(@RequestBody SendMailRequest request) throws NotFoundException {
        return mailService.sendMail(request);
    }
    @ApiOperation(value = "验证码校验",notes = "验证码校验")
    @GetMapping("/v1/mail/code")
    @ResponseStatus(HttpStatus.OK)
    public ActiveCodeResponse activeCode(@RequestBody ActiveCodeRequest request) throws NotFoundException{
        return mailService.activeCode(request);
    }
}
