package com.tcp.toeflserver.review;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
@Data
@Alias("review")
public class Review {
    private String place_id;
    private String user_id;
    private int score;
    private String content;
    private String date;

    public Review(String place_id, String user_id, int score, String content) {
        this.place_id = place_id;
        this.user_id = user_id;
        this.score = score;
        this.content = content;
    }

    public Review(Review review, String date) {
        this.place_id = review.place_id;
        this.user_id = review.user_id;
        this.score = review.score;
        this.content = review.content;
        this.date = date;
    }
}
