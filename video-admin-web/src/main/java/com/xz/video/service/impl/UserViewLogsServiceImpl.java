package com.xz.video.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.dao.UserDao;
import com.xz.video.dao.UserViewLogsDao;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.UserViewLogsEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.VideoWithViewLogsVo;
import com.xz.video.service.UserService;
import com.xz.video.service.UserViewLogsService;
import com.xz.video.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 用户浏览记录模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("userViewLogsService")
public class UserViewLogsServiceImpl extends ServiceImpl<UserViewLogsDao, UserViewLogsEntity> implements UserViewLogsService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    VideoService videoService;

    /**
     * 增加用户的浏览记录
     * @param userViewLogsEntity 用户浏览记录类
     */
    @Override
    public void addUserViewLogs(UserViewLogsEntity userViewLogsEntity) {
        if(userViewLogsEntity.getUserId()!=null&& StringUtils.isNotEmpty(userViewLogsEntity.getUserId())) {
            String key = "ViewLogs:" + userViewLogsEntity.getUserId();
            String hashKey = userViewLogsEntity.getVideoId();

            //往浏览记录缓存中存放数据,这里设置缓存不失效
            redisUtils.zAdd(key, hashKey, System.currentTimeMillis());
            //往视频表中添加一次浏览记录
            String videoKey = "videoViews";
            redisUtils.zIncrementScore(videoKey, userViewLogsEntity.getVideoId(), 1);
        }
    }

    /**
     * 得到用户的所有浏览记录
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 返回浏览记录集合
     */
    @Override
    public Map<String,Object> getAllUserViewLogs(long page,long limit, String userId) {

        Map<String,Object> map = new HashMap<>();
        List<VideoWithViewLogsVo> list = new ArrayList<>();
        String key = "ViewLogs:" + userId;

        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtils.zReverseRangeWithScores(key, (page-1)*limit, page*limit-1);
        Long count = redisUtils.zZCard(key);
        for (ZSetOperations.TypedTuple<String> e : typedTuples) {
            String videoId = e.getValue();
            long score = e.getScore().longValue();
            VideoWithViewLogsVo videoWithViewLogsVo = new VideoWithViewLogsVo();
            VideoEntity videoEntity = videoService.getOne(new QueryWrapper<VideoEntity>().eq("id", videoId));
            videoWithViewLogsVo.setUpdateTime(DateUtil.date(score));
            BeanUtils.copyProperties(videoEntity, videoWithViewLogsVo);
            list.add(videoWithViewLogsVo);
        }
        map.put("list",list);
        map.put("count",count);
        return map;
    }

    /**
     * 删除用户的浏览记录
     * @param userId 用户id
     * @param videoId 视频id
     */
    @Override
    public void delOneRecord(String userId, String videoId) {
        String key = "ViewLogs:" + userId;
        redisUtils.zRemove(key,videoId);
        UserViewLogsEntity userViewLogsEntity = this.baseMapper.selectOne(new QueryWrapper<UserViewLogsEntity>().and(e -> e.eq("user_id", userId)).eq("video_id", videoId));
        if(userViewLogsEntity!=null){
            this.baseMapper.deleteById(userViewLogsEntity);
        }
    }


}
