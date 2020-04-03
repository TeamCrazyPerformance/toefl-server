package com.tcp.toeflserver.review;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    public List<Review> getReviewsByUser(SelectReview selectReview){
        return reviewRepository.selectReviewsByUser(selectReview);
    }

    public List<Review> getReviewsByPlace(SelectReview selectReview){
        return reviewRepository.selectReviewsByPlace(selectReview);
    }

    public String removeReview(int index) {
        try {
            reviewRepository.deleteReview(index);
        }
        catch (Exception e){
           return e.getMessage();
        }
        return "Success";
    }

    public String addReview(Review review){
        try {
            Review addReview = new Review(review, getDate());
            reviewRepository.insertReview(addReview);
        }
        catch (Exception e){
            return e.getMessage();
        }
        return "Success";
    }

    private String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String date = dtf.format(now);

        return date;
    }

}
