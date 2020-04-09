package com.tcp.toeflserver.review;

import lombok.Data;

@Data
public class SelectReview {
    private String id;
    private int page;
    private String sort;
    private String order;
}
