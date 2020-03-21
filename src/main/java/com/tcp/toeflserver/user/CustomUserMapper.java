package com.tcp.toeflserver.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface CustomUserMapper {

    @Insert("insert into user( id, password, email, nickname ) values( #{id}, #{password}, #{email}, #{nickname} )")
    void insertUser(CustomUser user);

    @Select("select * from user where id=#{id}")
    CustomUser selectUserById(String id);

    @Select("select * from user where email=#{email}")
    CustomUser selectUserByEmail(String email);
}
