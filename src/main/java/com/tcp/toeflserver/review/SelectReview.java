package com.tcp.toeflserver.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectReview {
    private String id;
    private int page;
    private String sort;
    private String order;

    public SelectReview(String id, int page, String sort, String order){
        this.id = id;
        this.page = page;
        this.sort = sort;
        this.order = order;
    }
}
