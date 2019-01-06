package win.jieblog.questionnaire.utils;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import win.jieblog.questionnaire.configuration.OssConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 阿里云oss工具类
 */
@Component
@PropertySource("classpath:oss.properties")
public class OssHelper {
    @Value("${oss.endpoint}")
    private    String endpoint;
    @Value("${oss.accessKeyId}")
    private    String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private    String accessKeySecret;
    @Value("${oss.bucketName}")
    private    String bucketName;

    /**
     * 上传文件到oss
     * fileName需要与objectName保持一致
     * @param key 文件名
     * @param file 文件
     * @throws FileNotFoundException
     * @return 返回url
     */
    public  String uploadFile(String key, File file) throws FileNotFoundException {

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName,key,file);
        ossClient.putObject(bucketName, key, file);
        // 如果Object是公共读/公共读写权限，那么访问URL的格式为：Bucket名称.Endpoint/Object名称
        String url=bucketName+"."+endpoint+"/"+ossClient.getObject(bucketName,key);
        ossClient.shutdown();
        return url;
    }


}
