package com.xz.video.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.video.entity.UserEntity;
import com.xz.video.queryParams.UserQueryParams;
import com.xz.video.service.UserService;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后端页面用户模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/user")
public class UserController {

    @Autowired
    public UserService userService;


    /**
     * 得到所有的用户
     * @param current 分页
     * @param limit 分页大小
     * @param userQueryParams 查询条件
     * @return 用户集合
     */
    @RequestMapping("getAllUser/{current}/{limit}")
    public Result getAllUser(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) UserQueryParams userQueryParams) {
        Result<IPage<UserEntity>> result = new Result<>();
        IPage<UserEntity> pageInfo = userService.getAllUser(current, limit, userQueryParams);
        return result.ok(pageInfo);
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    @GetMapping("removeAll/{id}")
    public Result removeAll(@PathVariable String id) {
        Result result = new Result();
        userService.removeAll(id);
        return result.ok();
    }

    /**
     * 修改用户的状态
     * @param videoId 视频id
     * @param status 状态值
     */
    @GetMapping("updateStatus/{videoId}/{status}")
    public Result updateStatus(@PathVariable String videoId ,@PathVariable Integer status) {
        UserEntity user = userService.getById(videoId);
        user.setStatus(status);
        userService.updateById(user);
        return new Result().ok();
    }
}
