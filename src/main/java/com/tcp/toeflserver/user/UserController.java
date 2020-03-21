package com.tcp.toeflserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping
    public UserApiResponse signUp(@RequestBody CustomUser user) {
        UserApiResponse response = UserApiResponse.builder()
            .success(customUserDetailsService.signUp(user))
            .build();

        return response;
    }

    @PutMapping(value = "/id/confirmrepetition")
    public UserApiResponse idConfirmRepetition(@RequestBody HashMap<String, String> requestBody){
        UserApiResponse response = UserApiResponse.builder()
                .success(customUserDetailsService.isAvailableForId(requestBody.get("id")))
                .build();

        return response;
    }

    @PutMapping(value = "/nickname/confirmrepetition")
    public UserApiResponse nicknameConfirmRepetition(@RequestBody HashMap<String, String> requestBody){
        UserApiResponse response = UserApiResponse.builder()
                .success(customUserDetailsService.isAvailableForNickname(requestBody.get("nickname")))
                .build();

        return response;
    }
}