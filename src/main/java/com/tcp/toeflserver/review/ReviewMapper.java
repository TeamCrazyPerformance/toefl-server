package com.tcp.toeflserver.review;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewMapper {

    List<Review> selectReviewsByUserId(String userId);

    List<Review>selectReviewsByPlaceId(String placeId);

    void insertReview(Review review);

    void deleteReview(int reviewIndex);
}
