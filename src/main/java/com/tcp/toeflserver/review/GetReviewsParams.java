package com.tcp.toeflserver.review;

import lombok.Data;

@Data
public class GetReviewsParams {
    private String userId;
    private String placeId;
    private int page;
    private String sort;
    private String order;

    int getOffset() {
        return 20 * page;
    }
}
