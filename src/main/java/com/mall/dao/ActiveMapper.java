package com.mall.dao;

import com.mall.pojo.Active;

import java.util.List;

public interface ActiveMapper {
    int deleteByPrimaryKey(Integer activeId);

    int insert(Active record);

    int insertSelective(Active record);

    Active selectByPrimaryKey(Integer activeId);

    int updateByPrimaryKeySelective(Active record);

    int updateByPrimaryKey(Active record);

    List<Active> selectByactiveName(String activeName);

    List<Active> findAll();
}