package win.jieblog.questionnaire.service.servey;

import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.model.contract.servey.*;

public interface ServeyService {
    GlobalSearchForUserResponse globalSearchForUser(GlobalSearchForUserRequest request) ;
    ServeyDetailResponse serveyDetail(ServeyDetailRequest request);
    GenerateServeyResponse generateServey(GenerateServeyRequest request) throws DataBaseErrorException;
}
