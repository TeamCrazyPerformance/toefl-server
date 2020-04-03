package com.tcp.toeflserver.review;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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

    public String insertReview(Review review) {
        try {
            reviewMapper.insertReview(review);
            return "Success";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    public List<Review> selectReviewsByPlace(SelectReview selectReview) {
        List<Review> reviews = reviewMapper.selectReviewsByPlace(selectReview);
        return  reviews;
    }

    public List<Review> selectReviewsByUser(SelectReview selectReview) {
        List<Review> reviews = reviewMapper.selectReviewsByUser(selectReview);
        return  reviews;
    }
}
