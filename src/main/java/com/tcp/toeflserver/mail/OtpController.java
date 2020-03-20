package com.tcp.toeflserver.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    @Autowired
    public OtpService otpService;
    @Autowired
    public EmailService emailService;

    @PostMapping ("/user/email")
    public boolean generateOtp(@RequestParam String email){

        if(!emailService.isEmailFormat(email))
            return false;
        try {
            otpService.clearOTP(email);
            int otp = otpService.generateOTP(email);

            String message = "TOEFL validation CODE \n" + Integer.toString(otp);
            emailService.sendOtpMessage(email, "TOEFL sign up validation", message);
;
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @PostMapping("/user/email/validation")
    public boolean validateOtp(@RequestParam (name="email") String email, @RequestParam (name="validationCode") int otpnum){

        if(otpnum >= 0){
            int serverOtp = otpService.getOtp(email);
            if(serverOtp > 0 && otpnum == serverOtp){
                    otpService.clearOTP(email);
                    return true;
            }
        }
        return false;
    }
}