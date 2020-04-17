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
    public PlaceApiReponse getPlace(@PathVariable String placeId){
        Place place = placeService.getPlaceById(placeId);

        PlaceApiReponse response = PlaceApiReponse.builder()
                .place(place)
                .build();

        return response;
    }
}
