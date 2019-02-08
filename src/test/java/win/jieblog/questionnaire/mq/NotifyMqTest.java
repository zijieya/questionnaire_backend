package win.jieblog.questionnaire.mq;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class NotifyMqTest {
    @Autowired
    private NotifyMq notifyMq;
    @Test
    public void sendMessage() {
        notifyMq.sendMessage("test");
    }

    @Test
    public void receiveMessage() {
    }
}