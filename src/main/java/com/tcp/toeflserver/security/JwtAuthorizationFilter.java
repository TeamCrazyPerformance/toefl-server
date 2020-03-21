package com.tcp.toeflserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tcp.toeflserver.user.CustomUser;
import com.tcp.toeflserver.user.CustomUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final CustomUserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getUserDataAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUserDataAuthentication(HttpServletRequest request) {
        String header = Optional.ofNullable(request.getHeader(JwtProperties.HEADER_STRING)).orElse("");

        if(!header.startsWith(JwtProperties.TOKEN_PREFIX)){
            return null;
        }

        String id = JWT.require(Algorithm.HMAC256(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(header.replace(JwtProperties.TOKEN_PREFIX, ""))
                .getSubject();

        CustomUser user = Optional.ofNullable(userRepository.findUserById(id)).orElse(new CustomUser());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, null, user.getAuthorities());
        return authenticationToken;
    }
}
