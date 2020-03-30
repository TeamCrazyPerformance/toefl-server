package com.tcp.toeflserver.user;

import com.tcp.toeflserver.mail.ValidateCache;
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
    private final ValidateCache validateCache;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        CustomUser user = customUserRepository.findUserById(id);

        if(user == null){
            throw new UsernameNotFoundException(id);
        }

        return user;
    }

    public CustomUser findMyInformation(){
        return customUserRepository.findUserById(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public boolean signUp(CustomUser user){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(!validateCache.isValidated(user.getEmail())){
            return false;
        };

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

    public boolean isAvailableForEmail(String email){
        return customUserRepository.findUserByNickname(email) == null;
    }
}
