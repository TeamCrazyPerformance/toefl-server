package com.tcp.toeflserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/place/{placeId}")
    public ReviewApiResponse getReviewByPlace(@RequestParam SelectReview selectReview){
        List<Review> reviews = reviewService.getReviewsByPlace(selectReview);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .reviewList(reviews)
                .build();

        return response;
    }

    @PostMapping("/user/{userId}")
    public ReviewApiResponse postReviewsByUser(@RequestParam SelectReview selectReview){
        List<Review> reviews = reviewService.getReviewsByUser(selectReview);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .reviewList(reviews)
                .build();

        return response;
    }

    @PostMapping
    public ReviewApiResponse addReview(@RequestBody Review review){
        String added = reviewService.addReview(review);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(added)
                .build();

        return response;
    }

    @DeleteMapping("/{reviewId}")
    public ReviewApiResponse deleteReview(@PathVariable String reviewId){
        int index = Integer.parseInt(reviewId);
        String deleted = reviewService.removeReview(index);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(deleted)
                .build();

        return response;
    }

}