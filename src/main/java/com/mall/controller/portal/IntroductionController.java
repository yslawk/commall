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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private boolean addIntroduction(Introduction introduction, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        if (multipartFile != null) {
            //定义存储路径，这个路径可以随意改动，文件夹的名称的命名方式是根据添加数据的ID进行储存
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\introduction\\" + introduction.getIntroductionId() + "\\";
            File file2 = new File(path);
            //判断这个文件夹是否存在，不存在就创建
            if (!file2.exists()) {
                file2.mkdirs();
            }
            System.out.println(introduction.getIntroductionId());
            //判断文件上传是否为空，并且上传的长度
            if (multipartFile != null && multipartFile.length > 0) {
                for (int i = 0; i < multipartFile.length; i++) {
                    //定义将文件以文件数组的方式存放
                    MultipartFile file = multipartFile[i];
                    //得到文件的原始名称
                    String filename = file.getOriginalFilename();
                    //按循环的方式进行将图片命名，但是文件夹的名称的命名方式是根据添加数据的ID进行储存
                    String newFilename = i + filename.substring(filename.lastIndexOf("."));
                    //图片存储在这个ID下的文件夹
                    File file1 = new File(path + "\\" + newFilename);
                    file.transferTo(file1);
                    System.out.println(file);
                }
            }
        }
        System.out.println("添加成功");
        return introductionService.addIntroduction(introduction);
    }

    @RequestMapping(value = "updateIntroduction.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "运动介绍修改", notes = "运动介绍修改")
    private boolean updateIntroduction(Introduction introduction, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        if (multipartFile != null) {
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\introduction\\" + introduction.getIntroductionId() + "\\";
            File file2 = new File(path);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (multipartFile != null && multipartFile.length > 0) {
                for (int i = 0; i < multipartFile.length; i++) {
                    MultipartFile file = multipartFile[i];
                    String filename = file.getOriginalFilename();
                    String newFilename = i + filename.substring(filename.lastIndexOf("."));
                    File file1 = new File(path + "\\" + newFilename);
                    file.transferTo(file1);
                }
            }
        }
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
        System.out.println(introductionId);
        String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\introduction\\" + introductionId;
        File file = new File(path);
        //file.delete();
        if (!file.isDirectory()) {
            file.delete();
        } else {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File file1 = new File(path + "\\" + filelist[i]);
                //检查外层的文件夹，然后先进行里面的文件删除，再到外层
                if (!file1.isDirectory()) {
                    file1.delete();
                } else if (file1.isDirectory()) {
                    //file1(path+"\\"+filelist[i]);
                }
                file.delete();
            }
        }
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
