package com.tcp.toeflserver.review;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewMapper {

    @Select("SELECT reviews.* FROM ( SELECT * FROM review WHERE user_id=#{id} order by ${sort} ${order})reviews LIMIT #{page}, 20")
    @ResultType(com.tcp.toeflserver.review.Review.class)
    List<Review> selectReviewsByUser(SelectReview review);

    @Select("SELECT reviews.* FROM ( SELECT * FROM review WHERE place_id=#{id} order by ${sort} ${order})reviews LIMIT #{page}, 20")
    @ResultType(com.tcp.toeflserver.review.Review.class)
    List<Review> selectReviewsByPlace(SelectReview selectReview);

    @Insert("insert into review(place_id, user_id, score, date, content) values (#{placeId}, #{userId}, #{score}, #{date}, #{content})")
    void insertReview(Review review);
    @Delete("delete from review where index=#{index}")
    void deleteReview(int reviewIndex);
}
