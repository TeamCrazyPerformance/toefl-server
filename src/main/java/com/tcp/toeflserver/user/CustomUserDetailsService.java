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

    CustomUser findMyInformation(){
        return customUserRepository.findUserById(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    boolean signUp(CustomUser user){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(!isSeoultechEmailFormat(user.getEmail())){
            return false;
        }

        try{
            customUserRepository.saveUser(user);
            return true;
        } catch (DataAccessException e){
            return false;
        }
    }

    boolean isAvailableForId(String id){
        return customUserRepository.findUserById(id) == null;
    }

    boolean isAvailableForNickname(String nickname){
        return customUserRepository.findUserByNickname(nickname) == null;
    }

    boolean isAvailableForEmail(String email){
        return isSeoultechEmailFormat(email) && customUserRepository.findUserByEmail(email) == null;
    }

    private boolean isSeoultechEmailFormat(String email){
        String emailFormat = "^[a-zA-Z0-9._$+-]+@seoultech.ac.kr";
        return email.matches(emailFormat);
    }

    public void grantRoleUser() throws DataAccessException {
        customUserRepository.updateAuthority(findMyInformation().getId(), "ROLE_USER");
    }

    public String getOwnEmail() {
        return findMyInformation().getEmail();
    }

    boolean getOwnValidation(){
        return findMyInformation().getAuthority().equals("ROLE_USER");
    }
}
