package win.jieblog.questionnaire.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 头像工具类
 */
@Component
public class AvatarHelper {
    @Autowired
    OssHelper ossHelper;
    /**
     * 通过邮箱获取gravatar的头像url
     * @param email 邮箱
     * @return
     */
    public String getGravatarUrl(String email){
        String baseUrl="https://www.gravatar.com/avatar/";
        return baseUrl+Md5Helper.md5Hex(email.toLowerCase());//需要邮箱转为小写格式
    }
    public String getOssUrl(String key, File file) throws FileNotFoundException {
        return ossHelper.uploadFile(key,file);
    }
}
