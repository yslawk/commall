package com.mall.dao;

import com.mall.pojo.Notice;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(Notice record);

    List<Notice> selectByPrimaryTitle(String noticeTitle);


    int updateByPrimaryKey(Notice record);

    List<Notice> findAll();
}