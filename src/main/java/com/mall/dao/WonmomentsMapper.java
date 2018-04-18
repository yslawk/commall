package com.mall.dao;

import com.mall.pojo.Wonmoments;

import java.util.List;

public interface WonmomentsMapper {
    int deleteByPrimaryKey(Integer wonmomentId);

    int insert(Wonmoments record);

    int insertSelective(Wonmoments record);

    Wonmoments selectByPrimaryKey(Integer wonmomentId);

    int updateByPrimaryKeySelective(Wonmoments record);

    int updateByPrimaryKey(Wonmoments record);

    List<Wonmoments> selectByPrimaryTitle(String wonmomentTitle);

    List<Wonmoments> findAll();
}