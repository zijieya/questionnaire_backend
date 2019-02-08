package win.jieblog.questionnaire.mq;

//@Component
//public class NotifyMq {
//    private Logger logger= LoggerFactory.getLogger(NotifyMq.class);
//    private String NOTIFYDETINATION= MqDestinationName.NOTIFY.getName();
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public void sendMessage(String message){
//        Destination destination=new ActiveMQQueue(NOTIFYDETINATION);
//        jmsTemplate.convertAndSend(NOTIFYDETINATION,message);
//        logger.info(LogHelper.LogStatement("系统","发送通知消息","成功"));
//    }
//    @JmsListener(destination = "notify")
//    public void receiveMessage(String message){
//        System.out.println(message);
//        logger.info(LogHelper.LogStatement("系统","接收通知消息","成功"));
//    }
//}
