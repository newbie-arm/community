package com.newbie.community.service.impl;

import com.newbie.community.dto.QuestionDto;
import com.newbie.community.mapper.QuestionMapper;
import com.newbie.community.mapper.UserMapper;
import com.newbie.community.model.Question;
import com.newbie.community.model.User;
import com.newbie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void create(Question question) {
        questionMapper.create(question);
    }

    @Override
    public List<QuestionDto> findAllQuestion() {
        List<QuestionDto> allQuestion = questionMapper.findAllQuestion();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (QuestionDto question : allQuestion) {
             User user =  userMapper.findById(question.getCreator());
             question.setUser(user);
             questionDtoList.add(question);
        }
        return questionDtoList;
    }

}
