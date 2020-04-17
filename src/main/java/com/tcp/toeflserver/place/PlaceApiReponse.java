package com.tcp.toeflserver.place;

import com.tcp.toeflserver.review.Review;
import lombok.Builder;

import java.util.List;

public class PlaceApiReponse {
    String success;
    Place place;

    @Builder
    public PlaceApiReponse(String success, Place place) {
        this.success = success;
        this.place = place;
    }
}
