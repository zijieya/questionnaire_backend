package win.jieblog.questionnaire.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.jieblog.questionnaire.dao.QuestionMapper;
import win.jieblog.questionnaire.dao.ServeyMapper;
import win.jieblog.questionnaire.dao.ServeyResultMapper;
import win.jieblog.questionnaire.dao.UserMapper;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.exception.DataBaseErrorException;
import win.jieblog.questionnaire.model.contract.user.*;
import win.jieblog.questionnaire.model.dto.GetUsernameByUserserialId;
import win.jieblog.questionnaire.model.entity.Question;
import win.jieblog.questionnaire.model.entity.Servey;
import win.jieblog.questionnaire.model.entity.ServeyResult;
import win.jieblog.questionnaire.service.UserService;
import win.jieblog.questionnaire.utils.SerialsIdHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class UserServiceImpl implements UserService {
    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String ANSWERA="answerA";
    private static final String ANSWERB="answerB";
    private static final String ANSWERC="answerC";
    private static final String ANSWERD="answerD";
    private static final String ANSWERE="answerE";
    private static final String ANSWERF="answerF";
    private static final String ANSWERG="answerG";

    @Autowired
    ServeyMapper serveyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    ServeyResultMapper serveyResultMapper;
    /**
     * 问卷列表
     * @param request
     * @return
     * @throws AuthorityException
     */
    @Override
    public GlobalSearchForUserResponse globalSearchForUser(GlobalSearchForUserRequest request){
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        List<Servey> list=serveyMapper.selectByTagOrTitle(request.getKeyword());
        PageInfo pageInfo=new PageInfo(list);
        GlobalSearchForUserResponse response=new GlobalSearchForUserResponse();
        List<ServeyItem> serveyItemList=new ArrayList<>();
        List<String> listUserSerialsId=new ArrayList<>();//存储所有的userSerialsId
        if (list.size()>0){
            for (Servey servey:list){
               listUserSerialsId.add(servey.getCreatorserialid());
            }
        List<GetUsernameByUserserialId> usernameByUserserialIdList=userMapper.selectusernameByuserserialId(listUserSerialsId);
            if (usernameByUserserialIdList.size()>0){
                for (Servey servey:list){
                    ServeyItem item=new ServeyItem();
                    item.setSurveyserialid(servey.getSurveyserialid());//问卷序列号
                    item.setTag(servey.getTag());//标签
                    item.setTitle(servey.getTitle());//标题
                    item.setTotal(servey.getTotal());//总数
                    for(GetUsernameByUserserialId getUsernameByUserserialId:usernameByUserserialIdList){
                        if (getUsernameByUserserialId.getUserserialsId().equals(servey.getCreatorserialid())){
                            item.setCreator(getUsernameByUserserialId.getUsername());
                        }
                    }
                    serveyItemList.add(item);
                }
            }
        }
        response.setList(serveyItemList);
        response.setSuccessful(true);
        response.setTotal(pageInfo.getTotal());
        return response;
    }

    /**
     * 问卷详情
     * @param request
     * @return
     */
    @Override
    public ServeyDetailResponse serveyDetail(ServeyDetailRequest request) {
        ServeyDetailResponse response=new ServeyDetailResponse();
        Servey servey=serveyMapper.selectBySerialid(request.getSurveyserialid());
        List<QuestionItem> questionItemList=new ArrayList<>();
        List<Question> questionList=questionMapper.selectByServeyserialid(request.getSurveyserialid());
        List<String> listUserSerialsId=new ArrayList<>();
        listUserSerialsId.add(servey.getCreatorserialid());
        List<GetUsernameByUserserialId> usernameByUserserialIdList=userMapper.selectusernameByuserserialId(listUserSerialsId);
        GetUsernameByUserserialId getUsernameByUserserialId=usernameByUserserialIdList.get(0);
        response.setSurveyserialid(request.getSurveyserialid());
        response.setTag(servey.getTag());
        response.setTitle(servey.getTitle());
        response.setCreator(getUsernameByUserserialId.getUsername());
        //遍历赋值题目
        if (questionList.size()>0){
            for (Question question:questionList){
                QuestionItem questionItem=new QuestionItem();
                questionItem.setQuestionname(question.getQuestionname());
                if (!question.getAnswera().equals(" "))
                    questionItem.setAnswerA(question.getAnswera());
                if (!question.getAnswerb().equals(" "))
                    questionItem.setAnswerB(question.getAnswerb());
                if (!question.getAnswerc().equals(" "))
                    questionItem.setAnswerC(question.getAnswerc());
                if (!question.getAnswerd().equals(" "))
                    questionItem.setAnswerD(question.getAnswerd());
                if (!question.getAnswere().equals(" "))
                    questionItem.setAnswerE(question.getAnswere());
                if (!question.getAnswerf().equals(" "))
                    questionItem.setAnswerF(question.getAnswerf());
                if (!question.getAnswerg().equals(" "))
                    questionItem.setAnswerG(question.getAnswerg());
                questionItemList.add(questionItem);
            }
        }
     response.setList(questionItemList);
        return response;
    }

    /**
     * 生成问卷
     * @param request
     * @return
     */
    @Transactional
    @Override
    public GenerateServeyResponse generateServey(GenerateServeyRequest request) throws DataBaseErrorException {
        String serveySerialId=SerialsIdHelper.getSerialsId();
        GenerateServeyResponse response=new GenerateServeyResponse();
        // 插入servey表
        Servey servey=new Servey();
        servey.setCreatorserialid(request.getCreatorserialId());
        servey.setRemark(request.getRemark());
        servey.setSurveyserialid(serveySerialId);
        servey.setTag(request.getTag());
        servey.setTitle(request.getTitle());
        int total=serveyMapper.insertSelective(servey);
        List<QuestionInGeneration> list=request.getList();
        List<Question> questionList=new ArrayList<>();
        for (QuestionInGeneration questionInGeneration:list){
            Question question=new Question();
            question.setAnswera(questionInGeneration.getAnswerA());
            question.setAnswerb(questionInGeneration.getAnswerB());
            question.setAnswerc(questionInGeneration.getAnswerC());
            question.setAnswerd(questionInGeneration.getAnswerD());
            question.setAnswere(questionInGeneration.getAnswerE());
            question.setAnswerf(questionInGeneration.getAnswerF());
            question.setAnswerg(questionInGeneration.getAnswerG());
            question.setSurveyserialid(serveySerialId);
            question.setQuestionname(questionInGeneration.getQuestionname());
            question.setRemark(questionInGeneration.getRemark());
            questionList.add(question);
        }
        int total2=questionMapper.batchInsert(questionList);
        if (total!=1){
            logger.error("增加问卷异常");
            throw new DataBaseErrorException("插入异常",ErrorCode.INSERT_ERROR.getCode());
        }
        response.setSuccessful(true);
        logger.info("成功添加问卷");
        return response;
    }
    @Transactional
    @Override
    public SubmitServeyResponse submitServey(SubmitServeyRequest request) {
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
        SubmitServeyResponse response=new SubmitServeyResponse();
        response.setSuccessful(true);
        return response;
    }
}
