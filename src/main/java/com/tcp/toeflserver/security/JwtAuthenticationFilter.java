package com.tcp.toeflserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.toeflserver.data.LoginData;
import com.tcp.toeflserver.user.CustomUser;
import com.tcp.toeflserver.user.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginData credentials = null;

        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getId(),
                credentials.getPassword(),
                new ArrayList<>()
        );

        Authentication auth = authenticationManager.authenticate(authenticationToken);
        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUser user = (CustomUser) authResult.getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();
        UserApiResponse responseBody;

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET.getBytes()));

        responseBody = UserApiResponse.builder()
                .success(true)
                .token(JwtProperties.TOKEN_PREFIX + token)
                .userInformation(user)
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserApiResponse responseBody = UserApiResponse.builder()
                .success(false)
                .build();

        response.setStatus(403);
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}