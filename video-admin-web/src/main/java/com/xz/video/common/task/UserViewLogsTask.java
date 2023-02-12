package com.xz.video.common.task;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.UserViewLogsEntity;
import com.xz.video.service.UserViewLogsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 浏览记录服务类
 *
 */
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class UserViewLogsTask {


    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserViewLogsService userViewLogsService;
    /**
     * 添加浏览记录到数据库中
     * @throws InterruptedException
     */
    @Async
    @Scheduled(fixedDelay = 6000)  //间隔6秒
    public void addUserViewLogs() throws InterruptedException {
        Set<String> keys = redisUtils.getListKey("ViewLogs");

        for (String key:keys) {
            if(key!=null){
                Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtils.zReverseRangeWithScores(key, 0, -1);
                String[] split = key.split(":");
                String userId = split[split.length-1];
                for (ZSetOperations.TypedTuple<String> tuple:typedTuples) {
                    String videoId = tuple.getValue();
                    long score = tuple.getScore().longValue();
                    UserViewLogsEntity userViewLogsEntity = userViewLogsService.getOne(new QueryWrapper<UserViewLogsEntity>().and(e->e.eq("user_id",userId).eq("video_id",videoId)));

                    if(userViewLogsEntity!=null){
                        userViewLogsEntity.setGmtModified(DateUtil.date(score));
                        userViewLogsService.updateById(userViewLogsEntity);
                    }else{
                        UserViewLogsEntity entity = new UserViewLogsEntity();
                        entity.setUserId(userId);
                        entity.setVideoId(videoId);
                        entity.setGmtCreate(DateUtil.date(score));
                        entity.setGmtModified(DateUtil.date(score));
                        userViewLogsService.save(entity);
                    }
                }
            }
        }
    }
}
