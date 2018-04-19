package com.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.Notice;
import com.mall.service.NoticeService;
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
 * Created by  on 2018/4/12.
 */
@Controller
@RequestMapping(value = "/jx/Notice/")
@Api(value = "NoticeInfo", description = "赛事公告信息")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "addNotice.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "赛事公告添加", notes = "赛事公告添加")
    private boolean addNotice(Notice notice, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        if (multipartFile != null) {
            //定义存储路径，这个路径可以随意改动，文件夹的名称的命名方式是根据添加数据的ID进行储存
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\notice\\" + notice.getNoticeId() + "\\";
            File file2 = new File(path);
            //判断这个文件夹是否存在，不存在就创建
            if (!file2.exists()) {
                file2.mkdirs();
            }
            System.out.println(notice.getNoticeId());
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
        return noticeService.addNotice(notice);
    }

    @RequestMapping(value = "updateNotice.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "赛事公告修改", notes = "赛事公告修改")
    private boolean updateNotice(Notice notice, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        if (multipartFile != null) {
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\notice\\" + notice.getNoticeId() + "\\";
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
        return noticeService.addNotice(notice);
    }

    @RequestMapping(value = "deleteNotice.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "修改公告删除", notes = "修改公告删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（删除：delete；默认为删除）", required = false, paramType = "delete"),
            @ApiImplicitParam(name = "noticeId", value = "赛事公告id", required = true, paramType = "delete")
    })
    private boolean deleteNotice(Integer noticeId) {
        System.out.println(noticeId);
        String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\notice\\" + noticeId;
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
        return noticeService.deleteNotice(noticeId);
    }

    @RequestMapping(value = "findByNoticeTitle.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "赛事公告查询单个", notes = "赛事公告查询单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：findName；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "noticeTitle", value = "赛事公告主题", required = true, paramType = "query")
    })
    private List<Notice> findByNoticeTitle(String noticeTitle) {
        System.out.println("查询单个");
        return noticeService.findBynoticeTitle(noticeTitle);

    }

    @RequestMapping(value = "findAllNotice.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "赛事公告查询所有", notes = "赛事公告查询所有")
    private PageInfo<Notice> findAllNotice(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有");
        return noticeService.findAll(pageNum, pageSize);

    }
}
