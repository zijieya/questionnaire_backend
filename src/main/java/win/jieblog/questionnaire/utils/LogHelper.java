package win.jieblog.questionnaire.utils;

/**
 * 封装日志
 */
public class LogHelper {
    /**
     * 日志
     * @param username 用户唯一标识 如果是系统操作 则显示系统
     * @param operation 操作
     * @param result 结果
     * @return
     */
    public static String LogStatement(String username,String operation,String result){
        return "用户:"+username+" "+"操作"+operation+" "+"结果"+result;
    }

    /**
     *  错误异常日志
     * @param username 用户唯一标识 如果是系统操作 则显示系统
     * @param operation 操作
     * @param result 结果
     * @param description 描述
     * @return
     */
    public static String LogStatement(String username,String operation,String result,String description){
        return "用户:"+username+" "+"操作"+operation+" "+"结果"+result+" "+"描述"+description;
    }
}
