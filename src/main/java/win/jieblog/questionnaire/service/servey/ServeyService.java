package win.jieblog.questionnaire.service.servey;

import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.servey.*;

public interface ServeyService {
    GlobalSearchForUserResponse globalSearchForUser(GlobalSearchForUserRequest request) throws NotFoundException;
    ServeyDetailResponse serveyDetail(ServeyDetailRequest request) throws NotFoundException;
    GenerateServeyResponse generateServey(GenerateServeyRequest request) throws DataBaseErrorException, NotFoundException;
}
