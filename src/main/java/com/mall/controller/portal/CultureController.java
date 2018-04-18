package com.mall.controller.portal;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.github.pagehelper.PageInfo;
import com.mall.pojo.Culture;
import com.mall.service.CultureService;
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
@RequestMapping(value = "/jx/Culture/")
@Api(value = "CultureInfo", description = "文化板块信息")
public class CultureController {

    @Autowired
    private CultureService cultureService;

    @RequestMapping(value = "addCulture.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "文化板块添加", notes = "文化板块添加")
    public boolean addCulture(Culture culture) {
        System.out.println("添加成功");
        return cultureService.addCulture(culture);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "文化板块修改", notes = "文化板块修改")
    public boolean updateCulture(Culture culture) {
        System.out.println("修改成功");
        return cultureService.updateCulture(culture);
    }

    @RequestMapping(value = "deleteCulture.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "文化板块删除", notes = "文化板块删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（删除：delete；默认为查看）", required = false, paramType = "delete"),
            @ApiImplicitParam(name = "id", value = "文化id", required = true, paramType = "delete")
    })
    public boolean deleteCulture(Integer cultureId) {
        System.out.println("删除成功");
        return cultureService.deleteCulture(cultureId);
    }

    @RequestMapping(value = "findCultureBycultureName.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "文化板块查询单个", notes = "文化板块查询单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：findName；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "cultureName", value = "文化板块名称", required = true, paramType = "query")
    })
    public List<Culture> findCultureBycultureName(String cultureName) {
        System.out.println("查询单个");
        return cultureService.findCultureBycultureName(cultureName);
    }

    @RequestMapping(value = "findAllCulture.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "文化板块查询所有", notes = "文化板块查询所有")
    public PageInfo<Culture> findAllCulture(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有");
        return cultureService.findAllCulture(pageNum, pageSize);
    }
}
