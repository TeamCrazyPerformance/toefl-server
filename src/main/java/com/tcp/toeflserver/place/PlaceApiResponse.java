package com.tcp.toeflserver.place;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceApiResponse {
    boolean success;
    String errMsg;
    Place place;

    @Builder
    public PlaceApiResponse(boolean success, String errMsg, Place place) {
        this.success = success;
        this.errMsg = errMsg;
        this.place = place;
    }
}
