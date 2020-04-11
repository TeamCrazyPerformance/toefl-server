package com.tcp.toeflserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    List<Review> getReviewsByUser(SelectReview selectReview){
        return reviewRepository.selectReviewsByUser(selectReview);
    }

    List<Review> getReviewsByPlace(SelectReview selectReview){
        return reviewRepository.selectReviewsByPlace(selectReview);
    }

    String removeReview(int id) {
        String ownUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            if(!reviewRepository.selectReviewByIndex(id).getUserId().equals(ownUserId)){
                return "It's not yours";
            }
            reviewRepository.deleteReview(id);
            return "Success";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    String addReview(Review review){
        review.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        review.setTimeToNow();

        try {
            reviewRepository.insertReview(review);
            return "Success";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
