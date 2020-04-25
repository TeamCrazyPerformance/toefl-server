package com.tcp.toeflserver.place;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
class PlaceApiResponse {
    boolean success;
    String errMsg;
    float score;
}
