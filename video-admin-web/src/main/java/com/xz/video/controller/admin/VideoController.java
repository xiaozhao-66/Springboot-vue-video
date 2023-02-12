package com.xz.video.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.VideoInfoWithTagVo;
import com.xz.video.queryParams.VideoBindingTagsQueryParams;
import com.xz.video.queryParams.VideoQueryParams;
import com.xz.video.service.VideoService;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 视频模块
 * </p>
 *
 * @author 小赵
 * @since 2022-12-20
 */
@RestController
@RequestMapping("/video/videoInfo")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 根据分页条件得到视频列表
     * @param current 分页
     * @param limit 分页大小
     * @param videoQueryParams 查询条件
     * @return 视频信息集合
     */
    @PostMapping("pageCondition/{current}/{limit}")
    public Result pageCondition(@PathVariable long current, @PathVariable long limit,
                           @RequestBody(required = false) VideoQueryParams videoQueryParams) {
        Result<IPage<VideoEntity>> result = new Result<>();
        IPage<VideoEntity> pageInfo = videoService.pageCondition(current, limit, videoQueryParams);
        return result.ok(pageInfo);

    }

    /**
     * 保存视频信息
     * @param videoEntity 视频类
     * @return 视频id
     */
    @PostMapping("addVideoInfo")
    public Result addVideoInfo(@RequestBody VideoEntity videoEntity) {
        Result<String> result = new Result<>();
        String id = videoService.saveVideoInfo(videoEntity);
        return result.ok(id);
    }


    /**
     * 修改视频信息
     * @param videoBindingTagsQueryParams 视频实体类
     */
    @PostMapping("updateVideoInfo")
    public Result updateVideoInfo(@RequestBody VideoBindingTagsQueryParams videoBindingTagsQueryParams) {
        Result result = new Result();
        videoService.updateVideoInfo(videoBindingTagsQueryParams);
        return result.ok();
    }

    /**
     * 得到视频信息
     * @param videoId 视频id
     */
    @GetMapping("getVideoInfo/{Id}")
    public Result getVideoInfo(@PathVariable("Id")String videoId) {
        Result result = new Result();
        VideoInfoWithTagVo InfoVo = videoService.getVideoInfo(videoId);
        return result.ok(InfoVo);
    }

    /**
     * 删除视频
     * @param id 视频id
     */
    @GetMapping("removeAll/{id}")
    public Result removeAll(@PathVariable String id) {
        Result result = new Result();
        videoService.removeAll(id);
        return result.ok();
    }

    /**
     * 删除视频的播放地址
     * @param videoId 视频id
     */
    @RequestMapping("deletePlayerUrl/{videoId}")
    public Result deletePlayerUrl(@PathVariable String videoId) {
        Result result = new Result();

        videoService.deletePlayerUrl(videoId);
        return result.ok();
    }

    /**
     * 上传一条视频
     * @param videoBindingTagsQueryParams 视频实体类
     */
    @PostMapping("publish")
    public Result publish(@RequestBody VideoBindingTagsQueryParams videoBindingTagsQueryParams) {
        videoService.publish(videoBindingTagsQueryParams);
        return new Result().ok();
    }

    /**
     * 修改视频的状态
     * @param videoId 视频id
     * @param status 视频状态值
     */
    @GetMapping("updateStatus/{videoId}/{status}")
    public Result updateStatus(@PathVariable String videoId ,@PathVariable Integer status) {
        VideoEntity video = videoService.getById(videoId);
        video.setStatus(status);
        videoService.updateById(video);
        return new Result().ok();
    }

}
