package com.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.Place;
import com.mall.service.PlaceService;
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
@RequestMapping(value = "/jx/Place/")
@Api(value = "PlaceInfo", description = "极限场地信息")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "addPlace.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "极限场地添加", notes = "极限场地添加")
    private boolean addPlace(Place place) {
        System.out.println("添加成功");
        return placeService.addPlace(place);
    }

    @RequestMapping(value = "updatePlace.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "极限场地修改", notes = "极限场地修改")
    private boolean updatePlace(Place place) {
        System.out.println("修改成功");
        return placeService.updatePlace(place);
    }

    @RequestMapping(value = "deletePlace.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "极限场地删除", notes = "极限场地删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（删除：delete；默认为删除）", required = false, paramType = "delete"),
            @ApiImplicitParam(name = "placeId", value = "极限场地id", required = true, paramType = "delete")
    })
    private boolean deletePlace(Integer placeId) {
        System.out.println("删除成功");
        return placeService.deletePlace(placeId);
    }

    @RequestMapping(value = "findByplaceName.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "极限场地查询单个", notes = "极限场地查询单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：findByplaceName；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "findByplaceName", value = "极限场地名称", required = true, paramType = "query")
    })
    private List<Place> findByplaceName(String placeName) {
        System.out.println("查询单个");
        return placeService.findByplaceName(placeName);
    }

    @RequestMapping(value = "findAllPlace.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "极限场地查询所有", notes = "极限场地查询所有")
    private PageInfo<Place> findAllPlace(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有");
        return placeService.findAll(pageNum, pageSize);
    }
}
