package com.tcp.toeflserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ReviewApiResponse getReviewsByPlace(GetReviewsParams getReviewsParams) {
        List<Review> reviews = reviewService.getReviewsByPlace(getReviewsParams);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(reviews != null)
                .reviewList(Optional.ofNullable(reviews).orElse(new ArrayList<>()))
                .build();

        return response;
    }

    @GetMapping("/myreview")
    public ReviewApiResponse getMyReviews(GetReviewsParams getReviewsParams) {
        List<Review> reviews = reviewService.getMyReviews(getReviewsParams);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(reviews != null)
                .reviewList(Optional.ofNullable(reviews).orElse(new ArrayList<>()))
                .build();

        return response;
    }

    @PostMapping
    public ReviewApiResponse addReview(@RequestBody Review review) {
        ReviewApiResponse response;
        try{
            reviewService.addReview(review);
            response = ReviewApiResponse.builder()
                    .success(true)
                    .build();
        } catch (Exception e){
            response = ReviewApiResponse.builder()
                    .success(false)
                    .errMsg(e.getMessage())
                    .build();
        }

        return response;
    }

    @DeleteMapping("/{reviewId}")
    public ReviewApiResponse deleteReview(@PathVariable String reviewId) {
        ReviewApiResponse response;
        try{
            reviewService.removeReview(Integer.parseInt(reviewId));
            response = ReviewApiResponse.builder()
                    .success(true)
                    .build();
        } catch (Exception e){
            response = ReviewApiResponse.builder()
                    .success(false)
                    .errMsg(e.getMessage())
                    .build();
        }

        return response;
    }

}
