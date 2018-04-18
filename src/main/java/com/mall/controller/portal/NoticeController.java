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
    private boolean addNotice(Notice notice) {
        System.out.println("添加成功");
        return noticeService.addNotice(notice);
    }

    @RequestMapping(value = "updateNotice.do", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "赛事公告修改", notes = "赛事公告修改")
    private boolean updateNotice(Notice notice) {
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
