package com.mall.dao;

import com.mall.pojo.Encyclopedia;

import java.util.List;

public interface EncyclopediaMapper {
    int deleteByPrimaryKey(Integer encyclopediaId);

    int insert(Encyclopedia record);

    int insertSelective(Encyclopedia record);

    Encyclopedia selectByPrimaryKey(Integer encyclopediaId);

    int updateByPrimaryKeySelective(Encyclopedia record);

    int updateByPrimaryKey(Encyclopedia record);

    List<Encyclopedia> selectByPrimaryTitle(String encyclopediaTitle);

    List<Encyclopedia> findAll();
}