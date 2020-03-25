package com.tcp.toeflserver.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ReviewApiResponse {
    boolean success;
    List<Review> reviewList;

    @Builder
    public ReviewApiResponse(boolean success, List<Review> reviewList) {
        this.success = success;
        this.reviewList = reviewList;
    }
}
