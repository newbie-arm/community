package com.newbie.community.service;

import com.newbie.community.model.Question;
import org.springframework.stereotype.Service;


public interface QuestionService {
    void create(Question question);
}
