package com.tcp.toeflserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomUserDAO customUserDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        CustomUser user = customUserDAO.findUserById(id);
        return user;
    }

    public int signUpUser(CustomUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return customUserDAO.saveUser(user);
    }
}
