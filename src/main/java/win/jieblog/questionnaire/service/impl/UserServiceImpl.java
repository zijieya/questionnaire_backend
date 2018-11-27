package win.jieblog.questionnaire.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.jieblog.questionnaire.dao.QuestionMapper;
import win.jieblog.questionnaire.dao.ServeyMapper;
import win.jieblog.questionnaire.dao.UserMapper;
import win.jieblog.questionnaire.enums.ErrorCode;
import win.jieblog.questionnaire.exception.AuthorityException;
import win.jieblog.questionnaire.model.contract.user.*;
import win.jieblog.questionnaire.model.dto.GetUsernameByUserserialId;
import win.jieblog.questionnaire.model.entity.Question;
import win.jieblog.questionnaire.model.entity.Servey;
import win.jieblog.questionnaire.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ServeyMapper serveyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
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
        return response;
    }

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
}
