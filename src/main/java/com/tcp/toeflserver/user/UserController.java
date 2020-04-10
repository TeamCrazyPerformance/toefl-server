package com.tcp.toeflserver.user;

import com.tcp.toeflserver.mail.EmailService;
import com.tcp.toeflserver.mail.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;
    private final OtpService otpService;
    private final EmailService emailService;

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

    @GetMapping(value = "/mypage")
    public UserApiResponse getUserInformation() {
        CustomUser user = customUserDetailsService.findMyInformation();
        UserApiResponse response =UserApiResponse.builder()
                .success(user != null)
                .validated(customUserDetailsService.getOwnValidation())
                .userInformation(Optional.ofNullable(user).orElse(new CustomUser()))
                .build();

        return response;
    }

    @PostMapping ("/email/sendValidationCode")
    public UserApiResponse generateOtp(){
        UserApiResponse response = UserApiResponse.builder()
                .success(emailService.sendOtpMessage())
                .build();

        return response;
    }

    @PostMapping("/email/validation")
    public UserApiResponse validateOtp(@RequestBody HashMap<String, String> requestBody){
        UserApiResponse response = UserApiResponse.builder()
                .success(otpService.validateOtp(customUserDetailsService.getOwnEmail(), Integer.parseInt(requestBody.get("validationCode"))))
                .build();

        return response;
    }
}