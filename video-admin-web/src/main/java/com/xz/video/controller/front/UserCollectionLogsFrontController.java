package com.xz.video.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.entity.FollowEntity;
import com.xz.video.entity.UserCollectionLogsEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.service.UserCollectionLogsService;
import com.xz.video.service.VideoService;
import com.xz.video.entity.vo.CollectionLogVo;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/userCollectionLogs")
@Slf4j
public class UserCollectionLogsFrontController {

    @Autowired
    UserCollectionLogsService userCollectionLogsService;

    /**
     * 增加用户的收藏记录
     * @param collectionLogsEntity 收藏记录类
     */
    @RequestMapping("addUserCollectionLog")
    public Result addUserCollectionLog(@RequestBody UserCollectionLogsEntity collectionLogsEntity) {
        userCollectionLogsService.addUserCollectionLog(collectionLogsEntity);
        return new Result().ok();
    }

    /**
     * 得到用户的所有收藏
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 集合列表
     */
    @RequestMapping("getAllUserCollectionLogs/{page}/{limit}")
    public Result getAllUserCollectionLogs(@PathVariable long page, @PathVariable long limit, String userId) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String,Object> map  = userCollectionLogsService.getAllUserCollectionLogs(page, limit, userId);
        return result.ok(map);
    }

    /**
     * 删除用户收藏记录
     * @param collectionLogsEntity 收藏记录类
     */
    @RequestMapping("delUserCollectionLog")
    public Result delUserCollectionLog(@RequestBody UserCollectionLogsEntity collectionLogsEntity) {
        userCollectionLogsService.delUserCollectionLog(collectionLogsEntity);
        return new Result().ok();
    }

    /**
     * 检验用户是否收藏
     * @param collectionLogsEntity 收藏类
     */
    @RequestMapping("checkCollection")
    public Result checkCollection(@RequestBody UserCollectionLogsEntity collectionLogsEntity) {
        Result<Boolean> result = new Result<>();
        boolean flag = userCollectionLogsService.checkCollection(collectionLogsEntity);
        if (!flag) {
            return result.ok(false);
        }
        return result.ok(true);
    }

}
