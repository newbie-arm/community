package com.newbie.community.service;

import com.newbie.community.dto.QuestionDto;
import com.newbie.community.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuestionService {
    void create(Question question);

    List<QuestionDto> findAllQuestion();
}
