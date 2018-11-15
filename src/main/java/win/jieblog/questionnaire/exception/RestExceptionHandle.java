package win.jieblog.questionnaire.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import win.jieblog.questionnaire.model.entity.ErrorResult;

/**
 * 全局异常拦截
 */
@ControllerAdvice
public class RestExceptionHandle {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleNotFoundException(NotFoundException e){
        return new ErrorResult(e.getMessage(),e.getCode());
    }
}
