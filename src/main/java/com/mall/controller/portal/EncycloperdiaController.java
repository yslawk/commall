package com.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.Encyclopedia;
import com.mall.service.EncycloperdiaService;
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
@RequestMapping("/jx/Encycloperdia/")
@Api(value = "EncycloperdiaInfo", description = "极限百科表信息")
public class EncycloperdiaController {

    @Autowired
    private EncycloperdiaService encycloperdiaService;

    @RequestMapping(value = "addEncycloperdia.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "极限百科添加", notes = "极限百科添加")
    private boolean addEncycloperdia(Encyclopedia encyclopedia) {
        System.out.println("添加成功");
        return encycloperdiaService.addEncyclopedia(encyclopedia);
    }

    @RequestMapping(value = "updateEncycloperdia.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "极限百科修改", notes = "极限百科修改")
    private boolean updateEncycloperdia(Encyclopedia encyclopedia) {
        System.out.println("修改成功");
        return encycloperdiaService.updateEncyclopedia(encyclopedia);
    }

    @RequestMapping(value = "deleteEncycloperdia.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "极限百科删除", notes = "极限百科删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（删除：delete；默认为删除）", required = false, paramType = "delete"),
            @ApiImplicitParam(name = "id", value = "极限id", required = true, paramType = "delete")
    })
    private boolean deleteEncycloperdia(Integer encyclopediaName) {
        System.out.println("删除成功");
        return encycloperdiaService.deleteEncyclopedia(encyclopediaName);
    }

    @RequestMapping(value = "findEncyclopediaByencyclopediaTitle.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "极限百科查询单个", notes = "极限百科查询单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：findName；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "encyclopediaName", value = "极限百科名称", required = true, paramType = "query")
    })
    private List<Encyclopedia> findEncyclopediaByencyclopedia(String encyclopediaName) {
        System.out.println("查询单个");
        return encycloperdiaService.findEncyclopediaByencyclopediaTitle(encyclopediaName);
    }

    @RequestMapping(value = "findAllEncyclopedia.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "文化板块查询所有", notes = "文化板块查询所有")
    private PageInfo<Encyclopedia> findAllEncyclopedia(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有");
        return encycloperdiaService.findAll(pageNum, pageSize);
    }

}
