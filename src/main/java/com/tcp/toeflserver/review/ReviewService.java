package com.tcp.toeflserver.review;

import com.tcp.toeflserver.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceService placeService;

    List<Review> getReviewsByRequester(GetReviewsParams getReviewsParams) {
        getReviewsParams.setUserId(getOwnUserId());
        return reviewRepository.selectReviewsByUser(getReviewsParams);
    }

    private String getOwnUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    int countReviewsOfRequester(){
        return reviewRepository.countReviewsOfUser(getOwnUserId());
    }

    List<Review> getReviewsByPlace(GetReviewsParams getReviewsParams) {
        return reviewRepository.selectReviewsByPlace(getReviewsParams);
    }

    public int countReviewsOfPlace(String placeId) {
        return reviewRepository.countReviewsOfPlace(placeId);
    }

    @Transactional
    void removeReview(int id) throws Exception {
        Review targetReview = reviewRepository.selectReviewByIndex(id);

        if (!targetReview.getUserId().equals(getOwnUserId())) {
            throw new Exception("it's not yours");
        }

        reviewRepository.deleteReview(id);
        placeService.updatePlace(targetReview.getPlaceId());
    }

    @Transactional
    void addReview(Review review) throws Exception {
        review.setUserId(getOwnUserId());
        review.setTimeToNow();
        reviewRepository.insertReview(review);
        placeService.updatePlace(review.getPlaceId());
    }
}
