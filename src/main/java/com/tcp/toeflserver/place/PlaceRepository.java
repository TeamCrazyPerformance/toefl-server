package com.tcp.toeflserver.place;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceRepository {
    private final PlaceMapper placeMapper;

    public PlaceRepository(SqlSessionTemplate sqlSession){
        this.placeMapper = sqlSession.getMapper(PlaceMapper.class);
    }

    public void updatePlace(Place place){
        placeMapper.updatePlace(place);
    }

    int selectPlaceReviewScoreAverage(String placeId){
        return placeMapper.selectPlaceReviewAverageScore(placeId);
    }
}
