package com.tcp.toeflserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

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
    public UserApiResponse idConfirmRepetition(@RequestBody HashMap<String, String> requestBody) {
        UserApiResponse response = UserApiResponse.builder()
                .success(customUserDetailsService.isAvailableForId(requestBody.get("id")))
                .build();

        return response;
    }

    @PutMapping(value = "/nickname/confirmrepetition")
    public UserApiResponse nicknameConfirmRepetition(@RequestBody HashMap<String, String> requestBody) {
        UserApiResponse response = UserApiResponse.builder()
                .success(customUserDetailsService.isAvailableForNickname(requestBody.get("nickname")))
                .build();

        return response;
    }

    @GetMapping(value = "/{userId}")
    public UserApiResponse getUserInformation(@PathVariable("userId") String id) {
        CustomUser user = customUserDetailsService.findUserInformation(id);
        UserApiResponse response =UserApiResponse.builder()
                .success(user != null)
                .userInformation(Optional.ofNullable(user).orElse(new CustomUser()))
                .build();

        return response;
    }
}