package com.tcp.toeflserver.review;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewMapper {

    @Select("SELECT * FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY 'time' ${order}) rnum FROM review WHERE user_id=#{id})reviews WHERE rnum BETWEEN (#{page}-1)*20 AND #{page}*20")
    @ResultType(com.tcp.toeflserver.review.Review.class)
    List<Review> selectReviewsByUser(SelectReview review);

    @Select("SELECT * FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY #{sort} ${order}) rnum FROM review WHERE place_id=#{id})reviews WHERE rnum BETWEEN (#{page}-1)*20 AND #{page}*20")
    @ResultType(com.tcp.toeflserver.review.Review.class)
    List<Review> selectReviewsByPlace(SelectReview selectReview);

    @Insert("insert into review(index, place_id, user_id, score, date, content) values (#{index}, #{place_id}, #{user_id}, #{score}, #{date}, #{content})")
    void insertReview(Review review);
    @Delete("delete from review where index=#{index}")
    void deleteReview(int reviewIndex);
}
