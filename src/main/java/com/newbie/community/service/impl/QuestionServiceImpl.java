package com.newbie.community.service.impl;

import com.newbie.community.mapper.QuestionMapper;
import com.newbie.community.model.Question;
import com.newbie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper mapper;

    @Override
    public void create(Question question) {
        mapper.create(question);
    }
}
