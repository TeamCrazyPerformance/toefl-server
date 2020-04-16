package com.tcp.toeflserver.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Alias("review")
@JsonIgnoreProperties({"date", "userId"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Review {
    private String userId;
    private String placeId;
    private int score;
    private String content;
    private LocalDateTime date;
    private Timestamp timestamp;

    void setTimeToNow(){
        this.date = LocalDateTime.now();
        this.timestamp = Timestamp.valueOf(this.date);
    }
}
