package com.xz.video.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.entity.LikeItEntity;
import com.xz.video.service.LikeItService;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 点赞模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/likeIt")
@Slf4j
public class LikeItFrontController {

    @Autowired
    LikeItService likeItService;

    /**
     * 好评
     * @param likeItEntity 点赞类
     */
    @RequestMapping("support")
    public Result support(@RequestBody LikeItEntity likeItEntity){
        likeItService.support(likeItEntity);
        return new Result().ok();
    }

    /**
     * 差评
     * @param likeItEntity 点赞类
     */
    @RequestMapping("cancelSupport")
    public Result cancelSupport(@RequestBody LikeItEntity likeItEntity){
        likeItService.cancelSupport(likeItEntity);
        return new Result().ok();
    }

    /**
     * 查看当前评价类型
     * @param userId 用户id
     * @param videoId 视频id
     */
    @RequestMapping("getRecord/{userId}/{videoId}")
    public Result getRecord(@PathVariable String userId, @PathVariable String videoId){
        Result<Integer> result = new Result<>();
        LikeItEntity model = likeItService.getOne(new QueryWrapper<LikeItEntity>().and(e -> e.eq("video_id", videoId).eq("user_id", userId)));
        if(model!=null){
            return result.ok(model.getType());
        }
        return result.ok();
    }
}
