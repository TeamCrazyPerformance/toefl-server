package com.tcp.toeflserver.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ReviewApiResponse {
    boolean success;
    List<Review> reviewList;
    Integer page;
    String errMsg;
    String sortBy;
    String orderBy;
}
