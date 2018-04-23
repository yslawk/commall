package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.dao.EncyclopediaMapper;
import com.mall.pojo.Encyclopedia;
import com.mall.service.EncycloperdiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by  on 2018/4/10.
 */
@Service("EncycloperdiaService")
public class EncycloperdiaServiceImpl implements EncycloperdiaService {

    @Autowired
    private EncyclopediaMapper encyclopediaMapper;

    @Override
    public boolean addEncyclopedia(Encyclopedia encyclopedia) {
        boolean flag = false;
        try {
            encyclopediaMapper.insert(encyclopedia);
            flag = true;
        } catch (Exception e) {
            System.out.println("添加失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateEncyclopedia(Encyclopedia encyclopedia) {
        boolean flag = false;
        try {
            encyclopediaMapper.updateByPrimaryKey(encyclopedia);
            flag = true;
        } catch (Exception e) {
            System.out.println("修改失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteEncyclopedia(Integer encyclopediaId) {
        boolean flag = false;
        try {
            encyclopediaMapper.deleteByPrimaryKey(encyclopediaId);
            flag = true;
        } catch (Exception e) {
            System.out.println("删除失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Encyclopedia> findEncyclopediaByencyclopediaTitle(String encyclopediaTitle) {
        return (List<Encyclopedia>) encyclopediaMapper.selectByPrimaryTitle(encyclopediaTitle);
    }

    @Override
    public PageInfo<Encyclopedia> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Encyclopedia> encycloperdiaList = encyclopediaMapper.findAll();

        PageInfo<Encyclopedia> pageResult = new PageInfo<>(encycloperdiaList);
        return pageResult;

    }

    @Override
    public boolean encyclopediaUrlimg(String encyclopediaUrl, Integer encyclopediaId) {
        boolean flag = false;
        encyclopediaMapper.encyclopediaPhoto(encyclopediaUrl, encyclopediaId);
        return flag;

    }
}
