package com.tcp.toeflserver.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CustomUserMapper {

    @Insert("insert into user( id, password, email, nickname ) values( #{id}, #{password}, #{email}, #{nickname} )")
    void insertUser(CustomUser user);

    @Select("select * from user where id=#{id}")
    CustomUser selectUserById(String id);

    @Select("select * from user where email=#{email}")
    CustomUser selectUserByEmail(String email);

    @Select("select * from user where nickname=#{nickname}")
    CustomUser selectUserByNickname(String nickname);

    @Update("update user set authority=#{authority} where id=#{id}")
    void updateAuthority(String id, String authority);
}
