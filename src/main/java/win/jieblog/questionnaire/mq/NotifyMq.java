package win.jieblog.questionnaire.mq;

import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import win.jieblog.questionnaire.enums.MqDestinationName;
import win.jieblog.questionnaire.service.mail.impl.MailServeyImpl;
import win.jieblog.questionnaire.utils.LogHelper;

import javax.jms.Destination;

@Component
public class NotifyMq {
    private Logger logger= LoggerFactory.getLogger(NotifyMq.class);
    private String NOTIFYDETINATION= MqDestinationName.NOTIFY.getName();

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message){
        Destination destination=new ActiveMQQueue(NOTIFYDETINATION);
        jmsTemplate.convertAndSend(NOTIFYDETINATION,message);
        logger.info(LogHelper.LogStatement("系统","发送通知消息","成功"));
    }
    @JmsListener(destination = "notify")
    public void receiveMessage(String message){
        System.out.println(message);
        logger.info(LogHelper.LogStatement("系统","接收通知消息","成功"));
    }
}
