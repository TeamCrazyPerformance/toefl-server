package com.tcp.toeflserver.review;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("review")
public class Review {
    private String placeId;
    private String userId;
    private int score;
    private String content;
    private String date;

    public Review(Review review, String date) {
        this.placeId = review.placeId;
        this.userId = review.userId;
        this.score = review.score;
        this.content = review.content;
        this.date = date;
    }
}
