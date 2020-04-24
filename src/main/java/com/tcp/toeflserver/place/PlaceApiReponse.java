package com.tcp.toeflserver.place;

import com.tcp.toeflserver.review.Review;
import lombok.Builder;

import java.util.List;

public class PlaceApiReponse {
    boolean success;
    String errMsg;
    Place place;

    @Builder
    public PlaceApiReponse(boolean success, String errMsg,Place place) {
        this.success = success;
        this.errMsg = errMsg;
        this.place = place;
    }
}
