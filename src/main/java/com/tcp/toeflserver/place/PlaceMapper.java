package com.tcp.toeflserver.place;
import org.apache.ibatis.annotations.*;

public interface PlaceMapper {
    @ResultType(Place.class)
    Place selectPlaceById(String id);

    @Insert("insert into toefl.place(id, score) values(#{id}, #{score}) on duplicate key update score=#{score}")
    void updatePlace(Place place);

    @Select("select avg(score) from toefl.review where place_id=#{placeId}")
    @ResultType(Place.class)
    int selectPlaceReviewAverageScore(String placeId);
}
