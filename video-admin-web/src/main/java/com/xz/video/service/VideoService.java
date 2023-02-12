package com.xz.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.VideoInfoWithTagVo;
import com.xz.video.queryParams.VideoBindingTagsQueryParams;
import com.xz.video.queryParams.VideoFrontQueryParams;
import com.xz.video.queryParams.VideoQueryParams;

import java.util.List;

/**
 * <p>
 * 视频模块
 * </p>
 *
 * @author 小赵
 * @since 2022-12-20
 */
public interface VideoService extends IService<VideoEntity> {

    /**
     * 根据分页条件得到视频列表
     * @param page 分页
     * @param limit 分页大小
     * @param videoQueryParams 查询条件
     * @return 视频信息集合
     */
    IPage<VideoEntity> pageCondition(long page, long limit, VideoQueryParams videoQueryParams);

    /**
     * 保存视频信息
     * @param videoEntity 视频类
     * @return 视频id
     */
    String saveVideoInfo(VideoEntity videoEntity);

    /**
     * 删除视频
     * @param id 视频id
     */
    void removeAll(String id);

    /**
     * 获取受欢迎的视频
     * @return 视频集合
     */
    List<VideoEntity> getPopularVideos() throws InterruptedException;

    /**
     * 前台视频列表显示
     * @param page 分页
     * @param limit 分页大小
     * @param videoFrontQueryParams 查询条件
     * @return  视频集合
     */
    IPage<VideoEntity> pageFrontCondition(long page, long limit, VideoFrontQueryParams videoFrontQueryParams);

    /**
     * 上传一条视频
     * @param videoBindingTagsQueryParams 视频实体类
     */
    void publish(VideoBindingTagsQueryParams videoBindingTagsQueryParams);

    /**
     * 得到有联系的视频
     * @param videoId 视频id
     * @return 视频集合
     */
    List<VideoEntity> getRelatedVideos(String videoId);

    /**
     * 删除视频的播放地址
     * @param videoId 视频id
     */
    void deletePlayerUrl(String videoId);

    /**
     * 更改视频的观看次数
     * @param videoEntity 视频实体类
     */
    void updateViewCount(VideoEntity videoEntity);

    /**
     * 根据用户id得到当前用户上传的视频集合
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 视频集合
     */
    IPage<VideoEntity> getAllVideosWithUser(long page, long limit, String userId);

    VideoInfoWithTagVo getVideoInfo(String videoId);

    void updateVideoInfo(VideoBindingTagsQueryParams videoBindingTagsQueryParams);
}
