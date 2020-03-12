package com.newbie.community.service.impl;


import com.newbie.community.mapper.UserMapper;
import com.newbie.community.model.User;
import com.newbie.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public void addUser(User user) {
        mapper.addUser(user);
    }
}
