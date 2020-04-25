package com.tcp.toeflserver.place;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/place/{placeId}/star")
    public PlaceApiResponse getPlace(@PathVariable String placeId){
        PlaceApiResponse response;
        try {
            response = PlaceApiResponse.builder()
                    .success(true)
                    .score(placeService.getPlaceReviewScoreAverage(placeId))
                    .build();

        }
        catch (Exception e){
            response = PlaceApiResponse.builder()
                    .success(false)
                    .errMsg(e.getMessage())
                    .build();
        }

        return response;
    }
}
