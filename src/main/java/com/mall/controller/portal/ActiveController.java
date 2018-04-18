package com.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.Active;
import com.mall.service.ActiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by  on 2018/4/11.
 */
@Controller
@RequestMapping(value = "/jx/Active/")
@Api(value = "ActiveInfo", description = "活动回顾表信息")
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @RequestMapping(value = "addActive.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "活动回顾列表添加", notes = "活动回顾列表添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "multipartFile", value = "图片添加", dataType = "File", required = true, paramType = "add")
    })
    public boolean addActive(Active active, MultipartFile multipartFile) throws IllegalStateException, IOException {

        if (multipartFile != null) {
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\active\\";
            String filename = multipartFile.getOriginalFilename();
            //String newfilename= UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
            String newfilename = active.getActiveId() + filename.substring(filename.lastIndexOf("."));
            File file = new File(path + newfilename);
            multipartFile.transferTo(file);
        }
        System.out.println("添加成功");
        return activeService.addActive(active);
    }

    /*
        @RequestMapping(value="addphoto.do",method = RequestMethod.POST)
        @ResponseBody
        public  String addphoto(MultipartFile multipartFile)throws Exception{
            String path="D:\\img\\";
            String filename=multipartFile.getOriginalFilename();
            String newfilename= UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
            File file=new File(path+newfilename);
            multipartFile.transferTo(file);
            return "ok";
        }
    */
    @RequestMapping(value = "updateActive.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "活动回顾列表修改", notes = "活动回顾列表修改")
    public boolean updateActive(Active active) {
        System.out.println("修改成功");
        return activeService.updateActive(active);
    }

    @RequestMapping(value = "deleteActive.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "活动回顾列表删除", notes = "活动回顾列表删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（修改：update；默认为查看）", required = false, paramType = "delete"),
            @ApiImplicitParam(name = "id", value = "活动回顾id", required = true, paramType = "delete")

    })
    public boolean deleteActive(Integer activeId) {


        System.out.println("删除成功");
        return activeService.deleteActive(activeId);
    }

    @RequestMapping(value = "findActiveByactiveName.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "活动回顾查询", notes = "活动回顾查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：find；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "活动回顾id", required = true, paramType = "query")
    })
    public List<Active> findActiveByactiveName(String activeName) {
        System.out.println("查询单个开始");
        return activeService.findActiveByactiveName(activeName);
    }

    @RequestMapping(value = "findAllActive.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "活动回顾查询所有", notes = "活动回顾查询所有")
    public PageInfo<Active> findAllActive(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有开始");
        return activeService.findAllActive(pageNum, pageSize);
    }
}
