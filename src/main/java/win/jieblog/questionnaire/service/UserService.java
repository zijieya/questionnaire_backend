package win.jieblog.questionnaire.service;

import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.model.contract.user.GlobalSearchForUserRequest;
import win.jieblog.questionnaire.model.contract.user.GlobalSearchForUserResponse;
import win.jieblog.questionnaire.model.contract.user.ServeyDetailRequest;
import win.jieblog.questionnaire.model.contract.user.ServeyDetailResponse;

public interface UserService {
    GlobalSearchForUserResponse globalSearchForUser(GlobalSearchForUserRequest request) ;
    ServeyDetailResponse serveyDetail(ServeyDetailRequest request);
}
