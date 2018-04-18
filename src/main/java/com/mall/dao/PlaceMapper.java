package com.mall.dao;

import com.mall.pojo.Place;

import java.util.List;

public interface PlaceMapper {
    int deleteByPrimaryKey(Integer placeId);

    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(Integer placeId);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKey(Place record);

    List<Place> selectByPrimaryName(String placeName);

    List<Place> findAll();
}