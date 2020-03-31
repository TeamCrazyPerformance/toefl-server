package com.tcp.toeflserver.mail;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private static final Integer OTP_EXPIRE_MINUTES = 10;
    private final ValidateCache validateCache;
    private LoadingCache<String, Integer> otpCache;

    @Autowired
    public OtpService(ValidateCache validateCache){
        super();
        this.validateCache = validateCache;

        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(OTP_EXPIRE_MINUTES, TimeUnit.MINUTES).build(CacheLoader.from(key->null));
    }

    public int generateOtp(String key){
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

        otpCache.invalidate(key);
        validateCache.validate(key);
        return true;
    }

    private int getOtp(String key){
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return 0;
        }
    }
}
