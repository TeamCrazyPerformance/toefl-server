package com.tcp.toeflserver.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class UserApiResponse {
    boolean success;
    String token;
    CustomUser userInformation;

    @Builder
    public UserApiResponse(boolean success, String token, CustomUser userInformation){
        this.success = success;
        this.token = token;
        this.userInformation = userInformation;
    }
}
