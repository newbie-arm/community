package com.newbie.community.mapper;

import com.newbie.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values(#{name}, #{accountId}, #{token},#{gmtCreate},#{gmtModified})")
    void addUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);

}
