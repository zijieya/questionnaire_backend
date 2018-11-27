package win.jieblog.questionnaire.model.dto;

/**
 * userserialsId 和username转换
 */
public class GetUsernameByUserserialId {
    private String userserialid;//用户序列号
    private String username;//用户名

    public String getUserserialsId() {
        return userserialid;
    }

    public void setUserserialsId(String userserialid) {
        this.userserialid = userserialid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
