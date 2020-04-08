package com.tcp.toeflserver.review;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Alias("review")
public class Review {
    private String placeId;
    private String userId;
    private int score;
    private String content;
    private LocalDateTime date;
    private Timestamp timestamp;

    public Review(Review review) {
        this.placeId = review.placeId;
        this.userId = review.userId;
        this.score = review.score;
        this.content = review.content;
        this.date = LocalDateTime.now();
        this.timestamp = Timestamp.valueOf(this.date);
    }
}
