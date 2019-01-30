package win.jieblog.questionnaire.service.schedule.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import win.jieblog.questionnaire.dao.ServeyMapper;
import win.jieblog.questionnaire.service.schedule.ScheduleService;

import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ServeyMapper serveyMapper;
    private Logger logger= LoggerFactory.getLogger(ScheduleServiceImpl.class);
    int a=1;
    /**
     * 每分钟执行一次问卷过期检查
     */
    @Scheduled(cron = "0 * * * * *")
    @Override
    public void overDueServey() {
        Date date=new Date();
        serveyMapper.updateOvertimeServey(date);
    }
}
