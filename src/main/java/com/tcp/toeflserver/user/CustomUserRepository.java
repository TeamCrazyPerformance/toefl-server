package com.tcp.toeflserver.user;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepository {
    private final CustomUserMapper mapper;

    @Autowired
    public CustomUserRepository(SqlSessionTemplate sqlSession){
        this.mapper = sqlSession.getMapper(CustomUserMapper.class);
    }

    public CustomUser findUserById(String id){
        return mapper.selectUserById(id);
    }

    CustomUser findUserByEmail(String email)
    {
        return mapper.selectUserByEmail(email);
    }

    CustomUser findUserByNickname(String nickname){
        return mapper.selectUserByNickname(nickname);
    }

    void saveUser(CustomUser user) throws DataAccessException{
        mapper.insertUser(user);
    }

    void updateAuthority(String id, String authority) throws DataAccessException{
        mapper.updateAuthority(id, authority);
    }
}
