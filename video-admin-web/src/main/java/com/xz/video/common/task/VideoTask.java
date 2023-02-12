package com.xz.video.common.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.dao.VideoDao;
import com.xz.video.entity.VideoEntity;
import com.xz.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 视频服务类
 */
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class VideoTask {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    VideoService videoService;


    /**
     * 视频的浏览记录
     */
    @Async
    @Scheduled(fixedDelay = 6000)  //间隔6秒
    public void addVideoViews() throws InterruptedException {

        if(redisUtils.hasKey("videoViews")){
            Set<ZSetOperations.TypedTuple<String>> videoViews = redisUtils.zReverseRangeWithScores("videoViews", 0, -1);
            for (ZSetOperations.TypedTuple<String> e: videoViews) {
                String videoId = e.getValue();
                Double count = e.getScore();
                VideoEntity entity = videoService.getById(videoId);
                entity.setViewCount(count.longValue());
                videoService.updateViewCount(entity);
            }
        }else {
            String videoKey = "videoViews";
            List<VideoEntity> list = videoService.list();
            for (VideoEntity e:list) {
                redisUtils.zAdd(videoKey,e.getId(),e.getViewCount());
            }
        }
    }

}
