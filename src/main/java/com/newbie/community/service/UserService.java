package com.newbie.community.service;

import com.newbie.community.model.User;

public interface UserService {
    /**
    添加用github登陆的用户
     */
    void addUser(User user);

    /**
     * 用token查找用户
     * @param token
     * @return
     */
    User findByToken(String token);
}
