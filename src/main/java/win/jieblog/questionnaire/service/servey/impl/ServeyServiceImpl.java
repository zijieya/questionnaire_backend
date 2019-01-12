package win.jieblog.questionnaire.service.servey.impl;

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
import win.jieblog.questionnaire.exception.NotFoundException;
import win.jieblog.questionnaire.model.contract.servey.*;
import win.jieblog.questionnaire.model.dto.GetUsernameByUserserialId;
import win.jieblog.questionnaire.model.entity.Question;
import win.jieblog.questionnaire.model.entity.Servey;
import win.jieblog.questionnaire.model.entity.ServeyResult;
import win.jieblog.questionnaire.service.servey.ServeyService;
import win.jieblog.questionnaire.utils.LogHelper;
import win.jieblog.questionnaire.utils.SerialsIdHelper;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServeyServiceImpl implements ServeyService {
    private Logger logger= LoggerFactory.getLogger(ServeyServiceImpl.class);
    @Autowired
    private ServeyMapper serveyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ServeyResultMapper serveyResultMapper;
    /**
     * 问卷列表
     * @param request
     * @return
     * @throws AuthorityException
     */
    @Override
    public GlobalSearchForUserResponse globalSearchForUser(GlobalSearchForUserRequest request) throws NotFoundException {
//        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
//        List<Servey> list=serveyMapper.selectByTagOrTitle(request.getKeyword());
//        PageInfo pageInfo=new PageInfo(list);
        request.validate();
        List<Servey> list=new ArrayList<>();
        if (request.getSearchType()==1){
            PageHelper.startPage(request.getPageIndex(),request.getPageSize());
            list=serveyMapper.selectAllServeyByTagOrTitle(request.getKeyword());
        }
        if (request.getSearchType()==2){
            PageHelper.startPage(request.getPageIndex(),request.getPageSize());
            list=serveyMapper.selectMyServeyByTagOrTitle(request.getUserserialid(),request.getKeyword());
        }
        if (request.getSearchType()==3){
            List<ServeyResult> serveyResultList=serveyResultMapper.selectByAnswererserialId(request.getUserserialid());
            if (serveyResultList.size()>0){
                List<String> serveySerialIdList=new ArrayList<>();
                for(ServeyResult serveyResult:serveyResultList){
                    serveySerialIdList.add(serveyResult.getSurveyserialid());
                }
                PageHelper.startPage(request.getPageIndex(),request.getPageSize());
                list=serveyMapper.selectMyAnswerServeyByTagOrTitle(serveySerialIdList,request.getKeyword());
            }
        }
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
        logger.info(LogHelper.LogStatement("系统","搜索问卷","成功"));

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
    public ServeyDetailResponse serveyDetail(ServeyDetailRequest request) throws NotFoundException {
        request.validate();
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
        logger.info(LogHelper.LogStatement("系统","问卷详情","成功"));
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
    public GenerateServeyResponse generateServey(GenerateServeyRequest request) throws DataBaseErrorException, NotFoundException {
        request.validate();
        String serveySerialId= SerialsIdHelper.getSerialsId();
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
            logger.error(LogHelper.LogStatement("系统","增加问卷","失败","数据库插入异常"));
            throw new DataBaseErrorException("插入异常", ErrorCode.INSERT_ERROR.getCode());
        }
        logger.info(LogHelper.LogStatement("系统","增加问卷","成功"));
        response.setSuccessful(true);
        logger.info("成功添加问卷");
        return response;
    }
}
