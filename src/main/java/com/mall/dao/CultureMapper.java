package com.mall.dao;

import com.mall.pojo.Culture;

import java.util.List;

public interface CultureMapper {
    int deleteByPrimaryKey(Integer cultureId);

    int insert(Culture record);

    int insertSelective(Culture record);

    Culture selectByPrimaryKey(Integer cultureId);

    int updateByPrimaryKeySelective(Culture record);

    int updateByPrimaryKey(Culture record);

    List<Culture> selectByPrimaryName(String cultureName);

    List<Culture> findAll();
}