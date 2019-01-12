package win.jieblog.questionnaire.service.serveyresult.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.jieblog.questionnaire.dao.QuestionMapper;
import win.jieblog.questionnaire.dao.ServeyMapper;
import win.jieblog.questionnaire.dao.ServeyResultMapper;
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.serveyresult.AnswerInSubmitServey;
import win.jieblog.questionnaire.model.contract.serveyresult.SubmitServeyRequest;
import win.jieblog.questionnaire.model.contract.serveyresult.SubmitServeyResponse;
import win.jieblog.questionnaire.model.entity.Question;
import win.jieblog.questionnaire.model.entity.Servey;
import win.jieblog.questionnaire.model.entity.ServeyResult;
import win.jieblog.questionnaire.service.serveyresult.ServeyResultService;
import win.jieblog.questionnaire.utils.LogHelper;
import win.jieblog.questionnaire.utils.SerialsIdHelper;

import java.util.List;
@Service
public class ServeyResultServeyImpl implements ServeyResultService {
    private Logger logger= LoggerFactory.getLogger(ServeyResultServeyImpl.class);
    private static final String ANSWERA="answerA";
    private static final String ANSWERB="answerB";
    private static final String ANSWERC="answerC";
    private static final String ANSWERD="answerD";
    private static final String ANSWERE="answerE";
    private static final String ANSWERF="answerF";
    private static final String ANSWERG="answerG";
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ServeyResultMapper serveyResultMapper;
    @Autowired
    private ServeyMapper serveyMapper;
    /**
     * 提交问卷
     * @param request
     * @return
     */
    @Transactional
    @Override
    public SubmitServeyResponse submitServey(SubmitServeyRequest request) throws NotFoundException {
        request.validate();
        Servey servey=new Servey();
        servey.setSurveyserialid(request.getSurveyserialid());
        List<AnswerInSubmitServey> answerInSubmitServeyList= request.getList();
        List<Question> list=questionMapper.selectByServeyserialid(request.getSurveyserialid());
        for (Question question:list){
            for (AnswerInSubmitServey answerInSubmitServey:answerInSubmitServeyList){
                if (question.getQuestionid()==answerInSubmitServey.getQuestionid()){
                    //更新答案次数
                    if (ANSWERA.contains(answerInSubmitServey.getAnswer())){
                        question.setAnsweracount(question.getAnsweracount()+1);
                    }
                    if (ANSWERB.contains(answerInSubmitServey.getAnswer())){
                        question.setAnswerbcount(question.getAnswerbcount()+1);
                    }
                    if (ANSWERC.contains(answerInSubmitServey.getAnswer())){
                        question.setAnswerccount(question.getAnswerccount()+1);
                    }
                    if (ANSWERD.contains(answerInSubmitServey.getAnswer())){
                        question.setAnswerdcount(question.getAnswerdcount()+1);
                    }
                    if (ANSWERE.contains(answerInSubmitServey.getAnswer())){
                        question.setAnswerecount(question.getAnswerecount()+1);
                    }
                    if (ANSWERF.contains(answerInSubmitServey.getAnswer())){
                        question.setAnswerfcount(question.getAnswerfcount()+1);
                    }
                    if (ANSWERG.contains(answerInSubmitServey.getAnswer())){
                        question.setAnswergcount(question.getAnswergcount()+1);
                    }
                }
            }
        }
        // 更新商品信息
        int count=questionMapper.batchUpdateCount(list);
        servey.setTotal(servey.getTotal()+1);//次数加一
        // 更新问卷表
        int serveycount=serveyMapper.updateBySerialId(servey);
        // 1为注册用户填写 0为游客填写
        // 若是注册用户则更新问卷结果表
        if (request.getSubmitType()==1){
            ServeyResult serveyResult=new ServeyResult();
            // todo: 增加结果分析
            StringBuilder result=new StringBuilder();
            for (AnswerInSubmitServey submitServey:answerInSubmitServeyList){
                result.append(submitServey.getQuestionid()).append("-").append(submitServey.getAnswer()).append(",");
            }
            // 去除尾部的逗号
            serveyResult.setAnswer((String) result.subSequence(0,result.length()-1));
            serveyResult.setRemark(request.getRemark());
            serveyResult.setAnswererserialid(SerialsIdHelper.getSerialsId());
            serveyResult.setAnswererserialid(request.getAnswererserialId());
            int serveyResultCount=serveyResultMapper.insertSelective(serveyResult);
        }
        logger.info(LogHelper.LogStatement(request.getAnswererserialId(),"提交问卷答案","成功"));
        SubmitServeyResponse response=new SubmitServeyResponse();
        response.setSuccessful(true);
        return response;
    }
}
