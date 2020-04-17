package com.tcp.toeflserver.place;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("place")
public class Place {
    private String id;
    private float score;

    public Place(String id, float score) {
        this.id = id;
        this.score = score;
    }
}