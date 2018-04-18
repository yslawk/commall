package com.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.Introduction;
import com.mall.service.IntroductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by  on 2018/4/11.
 */
@Controller
@RequestMapping(value = "/jx/Introduction/")
@Api(value = "IntroductionInfo", description = "运动介绍信息")
public class IntroductionController {

    @Autowired
    private IntroductionService introductionService;

    @RequestMapping(value = "addIntroduction.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "运动介绍添加", notes = "运动介绍添加")
    private boolean addIntroduction(Introduction introduction) {
        System.out.println("添加成功");
        return introductionService.addIntroduction(introduction);
    }

    @RequestMapping(value = "updateIntroduction.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "运动介绍修改", notes = "运动介绍修改")
    private boolean updateIntroduction(Introduction introduction) {
        System.out.println("修改成功");
        return introductionService.updateIntroduction(introduction);
    }

    @RequestMapping(value = "deleteIntroduction.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "运动介绍删除", notes = "运动介绍删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（删除：delete；默认为删除）", required = false, paramType = "delete"),
            @ApiImplicitParam(name = "introductionId", value = "运动介绍id", required = true, paramType = "delete")
    })
    private boolean deleteIntroduction(Integer introductionId) {
        System.out.println("删除成功");
        return introductionService.delete(introductionId);
    }

    @RequestMapping(value = "findByintroductionTitle.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "运动介绍查询单个", notes = "运动介绍查询单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：findName；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "IntroductionTitle", value = "运动介绍主题", required = true, paramType = "query")
    })
    private List<Introduction> findByintroductionTitle(String IntroductionTitle) {
        System.out.println("查询单个");
        return introductionService.findByintroductionTitle(IntroductionTitle);
    }

    @RequestMapping(value = "findAllIntroduction.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "运动介绍查询所有", notes = "运动介绍查询所有")
    private PageInfo<Introduction> findAllIntroduction(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有");
        return introductionService.findAll(pageNum, pageSize);
    }
}
