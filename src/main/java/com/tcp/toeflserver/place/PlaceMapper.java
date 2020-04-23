package com.tcp.toeflserver.place;
import org.apache.ibatis.annotations.*;

public interface PlaceMapper {
    @ResultType(Place.class)
    Place selectPlaceById(String id);

    @Insert("insert into toefl.place(id, score) values(#{id}, #{score}) on duplicate key update score=#{score}")
    void updatePlace(Place place);

    @Select("select count(if(place_id=#{placeId}, 1, null)) from toefl.review")
    int selectPlaceCount(String placeId);

    @Select("select sum(score) from toefl.review where place_id=#{placeId}")
    int selectPlaceReviewScoreSum(String placeId);
}
