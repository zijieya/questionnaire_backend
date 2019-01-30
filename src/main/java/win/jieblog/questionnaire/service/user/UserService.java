package win.jieblog.questionnaire.service.user;

import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.session.RegisterRequest;
import win.jieblog.questionnaire.model.contract.session.RegisterResponse;
import win.jieblog.questionnaire.model.contract.user.*;

import java.io.IOException;

public interface UserService {
    RegisterResponse register(RegisterRequest request) throws NotFoundException;
    ResetPasswordResponse resetPassword(ResetPasswordRequest request) throws DataBaseErrorException, NotFoundException;
    UploadAvatarResponse uploadAvatar(UploadAvatarRequest request) throws IOException, NotFoundException, DataBaseErrorException;
    DeleteUserResponse deleteUser(DeleteUserRequest request) throws NotFoundException;
}
