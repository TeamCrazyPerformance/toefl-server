package com.tcp.toeflserver.place;
import org.apache.ibatis.annotations.*;

public interface PlaceMapper {
    @ResultType(Place.class)
    Place selectPlaceById(String id);

    @Insert("insert into place(id, score) values(#{id}, #{score})")
    void insertPlace(Place place);

    @Update("update place set score=#{score} where id=#{id}")
    void updatePlace(Place place);

    @Select("select count(if(place_id=#{placeId}, 1, null)) FROM toefl.review")
    int selectPlaceCount(String placeId);

    @Select("select sum(score) from toefl.review where place_id=#{placeId}")
    int selectPlaceReviewScoreSum(String placeId);
}