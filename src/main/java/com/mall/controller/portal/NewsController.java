package com.mall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.News;
import com.mall.service.NewsService;
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
@RequestMapping(value = "/jx/News/")
@Api(value = "NewsInfo", description = "赛事新闻信息")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "addNews.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "赛事新闻添加", notes = "赛事新闻添加")
    private boolean addNews(News news, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        if (multipartFile != null) {
            //定义存储路径，这个路径可以随意改动，文件夹的名称的命名方式是根据添加数据的ID进行储存
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\news\\" + news.getNewsId() + "\\";
            File file2 = new File(path);
            //判断这个文件夹是否存在，不存在就创建
            if (!file2.exists()) {
                file2.mkdirs();
            }
            System.out.println(news.getNewsId());
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
        return newsService.addNews(news);
    }

    @RequestMapping(value = "updateNews.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "赛事新闻修改", notes = "赛事新闻修改")
    private boolean updateNews(News news, @RequestParam MultipartFile[] multipartFile) throws IllegalStateException, IOException {
        if (multipartFile != null) {
            String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\news\\" + news.getNewsId() + "\\";
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
        return newsService.updateNews(news);
    }

    @RequestMapping(value = "deleteNews.do", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "赛事新闻删除", notes = "赛事新闻删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（删除：delete；默认为删除）", required = false, paramType = "delete"),
            @ApiImplicitParam(name = "NewsId", value = "赛事新闻id", required = true, paramType = "delete")
    })
    private boolean deleteNews(Integer newsId) {
        System.out.println(newsId);
        String path = "E:\\software\\jxx\\src\\main\\webapp\\img\\news\\" + newsId;
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
        return newsService.deleteNews(newsId);
    }

    @RequestMapping(value = " findBynewsTitle.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "赛事新闻查询单个", notes = "赛事新闻查询单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（查看：findName；默认为查看）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "newsTitle", value = "赛事新闻主题", required = true, paramType = "query")
    })
    private List<News> findBynewsTitle(String newsTitle) {
        System.out.println("查询单个");
        return newsService.findBynewsTitle(newsTitle);
    }

    @RequestMapping(value = "findAllNews.do", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "运动介绍查询所有", notes = "运动介绍查询所有")
    private PageInfo<News> findAllNews(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        System.out.println("查询所有");
        return newsService.findAll(pageNum, pageSize);
    }

}
