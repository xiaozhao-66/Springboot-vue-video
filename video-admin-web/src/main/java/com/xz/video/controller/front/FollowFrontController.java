package com.xz.video.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.entity.FollowEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.service.FanService;
import com.xz.video.service.FollowService;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关注用户模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/follow")
@Slf4j
public class FollowFrontController {

    @Autowired
    FollowService followService;

    /**
     * 根据用户id得到所有关注
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return map集合
     */
    @RequestMapping("getAllFollow/{page}/{limit}")
    public Result getAllFollow(@PathVariable long page, @PathVariable  long limit, String userId) {
        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> allFollow = followService.getAllFollow(page, limit, userId);
        return result.ok(allFollow);
    }

    /**
     * 添加关注
     * @param followEntity 关注实体类
     */
    @RequestMapping("addFollow")
    public Result addFollow(@RequestBody FollowEntity followEntity) {
        followService.addFollow(followEntity);
        return new Result().ok();
    }

    /**
     * 取消关注
     * @param followEntity 关注实体类
     */
    @RequestMapping("cancelFollow")
    public Result cancelFollow(@RequestBody FollowEntity followEntity) {
        followService.cancelFollow(followEntity);
        return new Result().ok();
    }

    /**
     * 查看是否关注当前用户
     * @param followEntity 关注用户类
     */
    @RequestMapping("checkFollow")
    public Result checkFollow(@RequestBody FollowEntity followEntity) {
        Result<Boolean> result = new Result<>();
        boolean flag = followService.checkFollow(followEntity);
        if (flag) {
            return result.ok(true);
        }
        return result.ok(false);
    }
}
