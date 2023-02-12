package com.xz.video.controller.front;

import cn.hutool.core.lang.hash.Hash;
import com.xz.video.entity.UserEntity;
import com.xz.video.service.FanService;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * <p>
 * 粉丝模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/fan")
@Slf4j
public class FanFrontController {

    @Autowired
    FanService fanService;

    /**
     * 根据用户id得到所有粉丝
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return map集合
     */
    @RequestMapping("getAllFan/{page}/{limit}")
    public Result getAllFan(@PathVariable long page, @PathVariable  long limit,String userId) {
        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> allFollow = fanService.getAllFan(page, limit, userId);
        return result.ok(allFollow);
    }
}
