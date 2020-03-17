package com.tcp.toeflserver.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public CustomUser findUserById(String id){
        return sqlSession.selectOne("user.selectUserById", id);
    }

    public CustomUser findUserByEmail(String email){
        return sqlSession.selectOne("user.selectUserByEmail", email);
    }

    public int saveUser(CustomUser user){
        //@TODO exception handling implement
        return sqlSession.insert("user.insertUser", user);
    }
}
