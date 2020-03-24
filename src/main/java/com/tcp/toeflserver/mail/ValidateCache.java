package com.tcp.toeflserver.mail;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ValidateCache {
    private static final Integer VALIDATE_EXPIRE_MINUTES = 60;
    private LoadingCache<String, Boolean> validateCache;

    public ValidateCache(){
        validateCache = CacheBuilder.newBuilder()
                .expireAfterWrite(VALIDATE_EXPIRE_MINUTES, TimeUnit.MINUTES).build(CacheLoader.from(key->null));
    }


    void validate(String key) {
        validateCache.invalidate(key);
        validateCache.put(key, true);
    }

    public boolean isValidated(String key) {
        try{
            return validateCache.get(key);
        } catch (Exception e){
            return false;
        }
    }
}
