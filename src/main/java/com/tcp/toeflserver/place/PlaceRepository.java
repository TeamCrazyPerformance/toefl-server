package com.tcp.toeflserver.place;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceRepository {
    private final PlaceMapper placeMapper;

    public PlaceRepository(SqlSessionTemplate sqlSession){
        this.placeMapper = sqlSession.getMapper(PlaceMapper.class);
    }

    Place selectPlaceById(String placeId){
        return placeMapper.selectPlaceById(placeId);
    }

    public void updatePlace(Place place){
        placeMapper.updatePlace(place);
    }
    public void insertPlace(Place place){
        placeMapper.insertPlace(place);
    }

    int selectPlaceCount(String placeId){
        return placeMapper.selectPlaceCount(placeId);
    }
    int selectPlaceReviewScoreSum(String placeId){
        return placeMapper.selectPlaceReviewScoreSum(placeId);
    }
}
