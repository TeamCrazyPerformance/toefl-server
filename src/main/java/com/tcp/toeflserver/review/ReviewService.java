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


    public List<Review> getReviewsByUserId(String user_id){
        return reviewRepository.selectReviewsByUserId(user_id);
    }

    public List<Review> getReviewsByPlaceId(String user_id){
        return reviewRepository.selectReviewsByPlaceId(user_id);
    }

    public boolean removeReview(int index){
        return reviewRepository.deleteReview(index);
    }

    public boolean addReview(Review review){
        return reviewRepository.insertReview(review);
    }

    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String date = dtf.format(now);

        return date;
    }
}
