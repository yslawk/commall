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
    private boolean addNews(News news) {
        System.out.println("添加成功");
        return newsService.addNews(news);
    }

    @RequestMapping(value = "updateNews.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "赛事新闻修改", notes = "赛事新闻修改")
    private boolean updateNews(News news) {
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
    private boolean deleteNews(Integer NewsId) {
        System.out.println("删除成功");
        return newsService.deleteNews(NewsId);
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
