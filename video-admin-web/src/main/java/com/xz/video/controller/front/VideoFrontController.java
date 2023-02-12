package com.xz.video.controller.front;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.video.common.client.VideoClient;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.VideoEntity;
import com.xz.video.queryParams.VideoBindingTagsQueryParams;
import com.xz.video.queryParams.VideoFrontQueryParams;
import com.xz.video.service.VideoService;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前台视频模块
 * </p>
 *
 * @author 小赵
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/videoInfo")
@Slf4j
public class VideoFrontController {

    @Autowired
    VideoService videoService;

    @Autowired
    VideoClient videoClient;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 前台视频列表显示
     * @param current 分页
     * @param limit 分页大小
     * @param videoFrontQueryParams 查询条件
     * @return  视频集合
     */
    @PostMapping("pageFrontCondition/{current}/{limit}")
    public Result pageFrontCondition(@PathVariable long current, @PathVariable long limit,
                                @RequestBody(required = false) VideoFrontQueryParams videoFrontQueryParams) {
        Result<IPage<VideoEntity>> result = new Result<>();
        IPage<VideoEntity> pageInfo = videoService.pageFrontCondition(current, limit, videoFrontQueryParams);
        return result.ok(pageInfo);
    }

    /**
     * 得到有联系的视频
     * @param videoId 视频id
     * @return 视频集合
     */
    @RequestMapping("getRelatedVideos/{videoId}")
    public Result getRelatedVideos(@PathVariable("videoId") String videoId) throws InterruptedException {
        Result<List<VideoEntity>> result = new Result<List<VideoEntity>>();
        List<VideoEntity> list = videoService.getRelatedVideos(videoId);
        return result.ok(list);
    }

    /**
     * 获取受欢迎的视频
     * @return 视频集合
     */
    @RequestMapping("getPopularVideos")
    public Result getPopularVideos() throws InterruptedException {
        Result<List<VideoEntity>> result = new Result<List<VideoEntity>>();
        List<VideoEntity> list = videoService.getPopularVideos();
        return result.ok(list);
    }

    /**
     * 根据用户id得到当前用户上传的视频集合
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 视频集合
     */
    @RequestMapping("getAllVideosWithUser/{page}/{limit}")
    public Result getAllVideosWithUser(@PathVariable long page, @PathVariable long limit, String userId) {
        Result<IPage<VideoEntity>> result = new Result<>();
        IPage<VideoEntity> pageInfo = videoService.getAllVideosWithUser(page,limit,userId);
        return result.ok(pageInfo);
    }

    /**
     * 上传一条视频
     * @param videoBindingTagsQueryParams 视频实体类
     */
    @RequestMapping("publish")
    public Result publish(@Valid  @RequestBody VideoBindingTagsQueryParams videoBindingTagsQueryParams) {
        videoService.publish(videoBindingTagsQueryParams);
        return new Result().ok();
    }
}
