package win.jieblog.questionnaire.service;

import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.model.contract.user.*;

public interface UserService {
    GlobalSearchForUserResponse globalSearchForUser(GlobalSearchForUserRequest request) ;
    ServeyDetailResponse serveyDetail(ServeyDetailRequest request);
    GenerateServeyResponse generateServey(GenerateServeyRequest request) throws DataBaseErrorException;
}
