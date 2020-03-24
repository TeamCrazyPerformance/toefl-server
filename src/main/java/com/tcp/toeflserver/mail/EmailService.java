package com.tcp.toeflserver.mail;

import com.tcp.toeflserver.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final OtpService otpService;
    private final CustomUserDetailsService userDetailsService;

    public boolean sendOtpMessage(String email) {
        SimpleMailMessage mailMessage;

        if(!isSeoultechEmailFormat(email) || !userDetailsService.isAvailableForEmail(email)){
            return false;
        }

        mailMessage = createOtpMailMessage(email);
        javaMailSender.send(mailMessage);
        return true;
    }

    private SimpleMailMessage createOtpMailMessage(String email){
        SimpleMailMessage mailMessage;
        String subject;
        String content;
        int otp;

        otp = otpService.generateOtp(email);

        subject = "TOEFL sign up validation";
        content = "TOEFL validation CODE \n" + otp;

        mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        return mailMessage;
    }

    private boolean isSeoultechEmailFormat(String email){
        String emailFormat = "^[a-zA-Z0-9._$+-]+@seoultech.ac.kr";
        return email.matches(emailFormat);
    }
}
