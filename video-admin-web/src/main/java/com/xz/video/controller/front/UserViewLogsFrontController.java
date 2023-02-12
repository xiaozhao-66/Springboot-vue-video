package com.xz.video.controller.front;

import com.xz.video.entity.UserViewLogsEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.VideoWithViewLogsVo;
import com.xz.video.service.UserViewLogsService;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户浏览记录模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/userViewLogs")
@Slf4j
public class UserViewLogsFrontController {

    @Autowired
    UserViewLogsService userViewLogsService;

    /**
     * 增加用户的浏览记录
     * @param userViewLogsEntity 用户浏览记录类
     */
    @RequestMapping("addUserViewLogs")
    public Result addUserViewLogs(@RequestBody UserViewLogsEntity userViewLogsEntity) {
        userViewLogsService.addUserViewLogs(userViewLogsEntity);
        return new Result().ok();
    }

    /**
     * 得到用户的所有浏览记录
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 返回浏览记录集合
     */
    @RequestMapping("getAllUserViewLogs/{page}/{limit}")
    public Result getAllUserViewLogs(@PathVariable long page, @PathVariable long limit, String userId) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String,Object> videoList = userViewLogsService.getAllUserViewLogs(page, limit, userId);
        return result.ok(videoList);
    }

    /**
     * 删除用户的浏览记录
     * @param userId 用户id
     * @param videoId 视频id
     */
    @RequestMapping("delOneRecord/{userId}/{videoId}")
    public Result delOneRecord(@PathVariable String userId,@PathVariable String videoId){
        userViewLogsService.delOneRecord(userId,videoId);
        return new Result().ok();
    }
}
