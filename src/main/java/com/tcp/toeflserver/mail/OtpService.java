package com.tcp.toeflserver.mail;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.tcp.toeflserver.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private static final Integer OTP_EXPIRE_MINUTES = 10;
    private final CustomUserDetailsService userDetailsService;
    private LoadingCache<String, Integer> otpCache;

    @Autowired
    public OtpService(CustomUserDetailsService userDetailsService){
        super();
        this.userDetailsService = userDetailsService;

        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(OTP_EXPIRE_MINUTES, TimeUnit.MINUTES).build(CacheLoader.from(key->null));
    }

    int generateOtp(String key){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        otpCache.invalidate(key);
        otpCache.put(key, otp);
        return otp;
    }

    public boolean validateOtp(String key, int value){
        int serverOtp = getOtp(key);

        if(serverOtp == 0 || value != serverOtp){
            return false;
        }

        try{
            userDetailsService.grantRoleUser();
            otpCache.invalidate(key);
            return true;
        } catch (DataAccessException e){
            return false;
        }
    }

    private int getOtp(String key){
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return 0;
        }
    }
}
