package com.tcp.toeflserver.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    Place getPlaceById(String placeId) {
        return placeRepository.selectPlaceById(placeId);
    }

    public void addPlace(String placeId) {
        Place place = getNewPlaceInfo(placeId);
        placeRepository.insertPlace(place);
    }

    public void updatePlace(String placeId) {
        Place place = getNewPlaceInfo(placeId);
        placeRepository.updatePlace(place);
    }

    private float getPlaceScoreAverage(String placeId) {
        return (float) placeRepository.selectPlaceReviewScoreSum(placeId) / (float) placeRepository.selectPlaceCount(placeId);
    }

    private Place getNewPlaceInfo(String placeId) {
        float score = getPlaceScoreAverage(placeId);
        Place place = new Place(placeId, score);

        return place;
    }
}