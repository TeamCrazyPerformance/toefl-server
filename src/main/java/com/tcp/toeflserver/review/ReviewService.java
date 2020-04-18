package com.tcp.toeflserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    List<Review> getReviewsByRequester(GetReviewsParams getReviewsParams) {
        getReviewsParams.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        return reviewRepository.selectReviewsByUser(getReviewsParams);
    }

    List<Review> getReviewsByPlace(GetReviewsParams getReviewsParams) {
        return reviewRepository.selectReviewsByPlace(getReviewsParams);
    }

    void removeReview(int id) throws Exception {
        String ownUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        Review targetReview = reviewRepository.selectReviewByIndex(id);

        if (!targetReview.getUserId().equals(ownUserId)) {
            throw new Exception("it's not yours");
        }

        reviewRepository.deleteReview(id);
    }

    void addReview(Review review) throws Exception {
        review.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        review.setTimeToNow();
        reviewRepository.insertReview(review);
    }
}
