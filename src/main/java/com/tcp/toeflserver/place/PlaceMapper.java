package com.tcp.toeflserver.place;
import org.apache.ibatis.annotations.*;

public interface PlaceMapper {

    @Insert("insert into toefl.place(id, score) values(#{id}, #{score}) on duplicate key update score=#{score}")
    void updatePlace(Place place);

    @Select("select avg(score) from toefl.review where place_id=#{placeId}")
    float selectPlaceReviewAverageScore(String placeId);
}
