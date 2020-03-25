package com.tcp.toeflserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/place/{placeId}")
    public ReviewApiResponse getReviewByPlaceId(@PathVariable String placeId){
        List<Review> reviews = reviewService.getReviewsByPlaceId(placeId);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .reviewList(reviews)
                .build();

        return response;
    }

    @PostMapping("/user/{userId}")
    public ReviewApiResponse getReviewsByUserId(@PathVariable String userId){
        List<Review> reviews = reviewService.getReviewsByUserId(userId);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .reviewList(reviews)
                .build();

        return response;
    }

    @PostMapping
    public ReviewApiResponse addReview(@RequestBody HashMap<String, String> requestBody){
        Review review = new Review(requestBody.get("place_id"), requestBody.get("user_id"),
                Integer.parseInt(requestBody.get("score")),reviewService.getDate(),
                requestBody.get("content"));

        boolean added = reviewService.addReview(review);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(added)
                .build();

        return response;
    }

    @DeleteMapping("/{reviewId}")
    public ReviewApiResponse deleteReview(@PathVariable String reviewId){
        int index = Integer.parseInt(reviewId);
        boolean deleted = reviewService.removeReview(index);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(deleted)
                .build();

        return response;
    }

}
