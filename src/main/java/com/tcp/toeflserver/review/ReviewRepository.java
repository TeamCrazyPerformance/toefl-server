package com.tcp.toeflserver.review;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReviewRepository {

    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewRepository(SqlSessionTemplate sqlSession){
        this.reviewMapper = sqlSession.getMapper(ReviewMapper.class);
    }

    public boolean deleteReview(int index) {
        reviewMapper.deleteReview(index);
        return true;
    }

    public boolean insertReview(Review review) {
        reviewMapper.insertReview(review);
        return true;
    }

    public List<Review> selectReviewsByPlaceId(String placeId) {
        List<Review> reviews = reviewMapper.selectReviewsByPlaceId(placeId);
        return  reviews;
    }

    public List<Review> selectReviewsByUserId(String userId) {
        List<Review> reviews = reviewMapper.selectReviewsByUserId(userId);
        return  reviews;
    }
}
