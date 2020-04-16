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
    public ReviewApiResponse getReviewByPlace(SelectReview selectReview) {
        List<Review> reviews = reviewService.getReviewsByPlace(selectReview);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(reviews != null)
                .reviewList(Optional.ofNullable(reviews).orElse(new ArrayList<>()))
                .build();

        return response;
    }

    @GetMapping("/myreview")
    public ReviewApiResponse getReviewsByUser(SelectReview selectReview) {
        List<Review> reviews = reviewService.getReviewsByUser(selectReview);

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(reviews != null)
                .reviewList(Optional.ofNullable(reviews).orElse(new ArrayList<>()))
                .build();

        return response;
    }

    @PostMapping
    public ReviewApiResponse addReview(@RequestBody Review review) {
        String added = reviewService.addReview(review);
        boolean success = added.equals("Success");

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(success)
                .errMsg(success ? null : added)
                .build();

        return response;
    }

    @DeleteMapping("/{reviewId}")
    public ReviewApiResponse deleteReview(@PathVariable String reviewId) {
        String deleted = reviewService.removeReview(Integer.parseInt(reviewId));
        boolean success = deleted.equals("Success");

        ReviewApiResponse response = ReviewApiResponse.builder()
                .success(success)
                .errMsg(success ? null : deleted)
                .build();

        return response;
    }

}
