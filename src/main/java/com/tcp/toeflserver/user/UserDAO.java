package com.tcp.toeflserver.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public CustomUser getUserById(String id){
        return sqlSession.selectOne("user.selectUserById", id);
    }

    public CustomUser getUserByEmail(String email){
        return sqlSession.selectOne("user.selectUserByEmail", email);
    }
}
