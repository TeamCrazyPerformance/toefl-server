package com.tcp.toeflserver.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public void updatePlace(String placeId) {
        placeRepository.updatePlace(new Place(placeId, getPlaceReviewScoreAverage(placeId)));
    }

    public float getPlaceReviewScoreAverage(String placeId) {
        return placeRepository.selectPlaceReviewScoreAverage(placeId);
    }
}