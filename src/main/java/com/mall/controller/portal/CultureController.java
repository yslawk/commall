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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public boolean addCulture(Culture culture, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        /*
        //第一个方法代表单个文件上传
        if (multipartFile != null) {
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\active\\";
            String filename = multipartFile.getOriginalFilename();
            //String newfilename= UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
            String newfilename = active.getActiveId() + filename.substring(filename.lastIndexOf("."));
            File file = new File(path + newfilename);
            multipartFile.transferTo(file);
        }*/
        if (multipartFile != null) {
            //定义存储路径，这个路径可以随意改动，文件夹的名称的命名方式是根据添加数据的ID进行储存
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\cultrue\\" + culture.getCultureId() + "\\";
            File file2 = new File(path);
            //判断这个文件夹是否存在，不存在就创建
            if (!file2.exists()) {
                file2.mkdirs();
            }
            System.out.println(culture.getCultureId());
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
        return cultureService.addCulture(culture);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "文化板块修改", notes = "文化板块修改")
    public boolean updateCulture(Culture culture, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        if (multipartFile != null) {
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\cultrue\\" + culture.getCultureId() + "\\";
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
        System.out.println(cultureId);
        String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\cultrue\\" + cultureId;
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
