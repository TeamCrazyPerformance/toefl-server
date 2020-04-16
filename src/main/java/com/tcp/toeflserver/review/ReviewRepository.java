package com.tcp.toeflserver.review;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ReviewRepository {

    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewRepository(SqlSessionTemplate sqlSession){
        this.reviewMapper = sqlSession.getMapper(ReviewMapper.class);
    }

    public void deleteReview(int index) throws DataAccessException {
        reviewMapper.deleteReview(index);
    }

    public void insertReview(Review review) throws DataAccessException {
        reviewMapper.insertReview(review);
    }

    public List<Review> selectReviewsByPlace(SelectReview selectReview) {
        return reviewMapper.selectReviewsByPlace(selectReview);
    }

    public List<Review> selectReviewsByUser(SelectReview selectReview) {
        return reviewMapper.selectReviewsByUser(selectReview);
    }

    public Review selectReviewByIndex(int id) {
        return reviewMapper.selectReviewByIndex(id);
    }
}
