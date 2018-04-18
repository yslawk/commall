package com.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.Wonmoments;
import com.mall.service.WonmomentsService;
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
 * Created by  on 2018/4/13.
 */
@Controller
@RequestMapping(value = "/jx/Wonmoments/")
@Api(value = "WonmomentsInfo", description = "精彩瞬间信息")
public class WonmomentsController {

    @Autowired
    private WonmomentsService wonmomentsService;

    @RequestMapping(value = "addWonmoments.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "精彩瞬间添加", notes = "精彩瞬间添加")
    private boolean addWonmoments(Wonmoments wonmoments) {
        System.out.println("添加成功");
        return wonmomentsService.addWonmoments(wonmoments);
    }

    @RequestMapping(value = "updateWonmoments.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "精彩瞬间修改", notes = "精彩瞬间修改")
    private boolean updateWonmoments(Wonmoments wonmoments) {
        System.out.println("修改成功");
        return wonmomentsService.updateWonmoments(wonmoments);
    }

    @RequestMapping(value = "deleteWonmoments.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "精彩瞬间删除", notes = "精彩瞬间删除")
    private boolean deleteWonmoments(Integer wonmomentsId) {
        System.out.println("删除成功");
        return wonmomentsService.deleteWonmoments(wonmomentsId);
    }

    @RequestMapping(value = "findByplaceName.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "精彩瞬间查询单个", notes = "精彩瞬间查询单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：findByWonmomentsName；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "findBywonmomentTitle", value = "精彩瞬间主题", required = true, paramType = "query")
    })
    private List<Wonmoments> findBywonmomentTitle(String wonmomentsTitle) {
        System.out.println("查询单个");
        return wonmomentsService.findBywonmomentTitle(wonmomentsTitle);
    }

    @RequestMapping(value = "findAllWonmoment.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "精彩瞬间查询所有", notes = "精彩瞬间查询所有")
    private PageInfo<Wonmoments> findAllWonmoment(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有");
        return wonmomentsService.findAll(pageNum, pageSize);
    }
}
