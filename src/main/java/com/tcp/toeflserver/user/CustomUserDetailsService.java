package com.tcp.toeflserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        CustomUser user = customUserRepository.findUserById(id);

        if(user == null){
            throw new UsernameNotFoundException(id);
        }

        return user;
    }

    public CustomUser findUserInformation(String id){
        if(!id.equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            return null;
        }

        return customUserRepository.findUserById(id);
    }

    public boolean signUp(CustomUser user){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try{
            customUserRepository.saveUser(user);
            return true;
        } catch (DataAccessException e){
            return false;
        }
    }

    public boolean isAvailableForId(String id){
        return customUserRepository.findUserById(id) == null;
    }

    public boolean isAvailableForNickname(String nickname){
        return customUserRepository.findUserByNickname(nickname) == null;
    }
}
