package com.tcp.toeflserver.review;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewMapper {

    @Select("select * from review where user_id=#{userId}")
    @ResultType(Review.class)
    List<Review> selectReviewsByUserId(String userId);

//    @Select("select * from review where place_id=#{placeId}")
    @Select("select review.* from (select row_number over(order by review.#{sort_type}) num, * from review where place_id=#{place_id}) where row_number between #{first} and #{last}")
    @ResultType(Review.class)
    List<Review>selectReviewsByPlaceId(String placeId);

    @Insert("insert into review(index, place_id, user_id, score, date, content) values (#{index}, #{place_id}, #{user_id}, #{score}, #{date}, #{content})")
    void insertReview(Review review);

    @Delete("delete from review where index=#{index}")
    void deleteReview(int reviewIndex);
}
