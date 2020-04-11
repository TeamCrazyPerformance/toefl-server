package com.tcp.toeflserver.review;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewMapper {

    @Select("SELECT reviews.* FROM ( SELECT * FROM review WHERE user_id=#{userId} order by ${sort} ${order})reviews LIMIT #{offset}, 20")
    @ResultType(com.tcp.toeflserver.review.Review.class)
    List<Review> selectReviewsByUser(SelectReview review);

    @Select("SELECT reviews.* FROM ( SELECT * FROM review WHERE place_id=#{placeId} order by ${sort} ${order})reviews LIMIT #{offset}, 20")
    @ResultType(com.tcp.toeflserver.review.Review.class)
    List<Review> selectReviewsByPlace(SelectReview selectReview);

    @Insert("insert into review(place_id, user_id, score, date, content) values (#{placeId}, #{userId}, #{score}, #{date}, #{content})")
    void insertReview(Review review);
    @Delete("delete from review where id = #{id}")
    void deleteReview(int id);

    @Select("SELECT * FROM review WHERE id = #{id}")
    Review selectReviewByIndex(int id);
}
