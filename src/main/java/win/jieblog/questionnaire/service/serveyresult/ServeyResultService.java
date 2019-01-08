package win.jieblog.questionnaire.service.serveyresult;

import win.jieblog.questionnaire.model.contract.serveyresult.SubmitServeyRequest;
import win.jieblog.questionnaire.model.contract.serveyresult.SubmitServeyResponse;

public interface ServeyResultService {
    SubmitServeyResponse submitServey(SubmitServeyRequest request);
}
