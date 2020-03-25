package com.tcp.toeflserver.review;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
@Data
@Alias("review")
public class Review {
    private String place_id;
    private String user_id;
    private int score;
    private String date;
    private String content;

    public Review(String place_id, String user_id, int score, String date, String content) {
        this.place_id = place_id;
        this.user_id = user_id;
        this.score = score;
        this.date = date;
        this.content = content;
    }
}
