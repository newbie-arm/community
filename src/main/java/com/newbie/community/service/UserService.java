package com.newbie.community.service;

import com.newbie.community.model.User;

public interface UserService {
    void addUser(User user);

    User findByToken(String token);
}
