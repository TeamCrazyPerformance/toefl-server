package com.tcp.toeflserver.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public void updatePlace(String placeId) {
        Place place = getNewPlaceInfo(placeId);
        placeRepository.updatePlace(place);
    }

    public Place getNewPlaceInfo(String placeId) {
        float score = getPlaceReviewScoreAverage(placeId);
        Place place = new Place(placeId, score);

        return place;
    }

    private float getPlaceReviewScoreAverage(String placeId) {
        return (float) placeRepository.selectPlaceReviewScoreAverage(placeId);
    }
}