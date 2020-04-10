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

    public boolean sendOtpMessage() {
        SimpleMailMessage mailMessage = createOtpMailMessage(userDetailsService.getOwnEmail());
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
}
