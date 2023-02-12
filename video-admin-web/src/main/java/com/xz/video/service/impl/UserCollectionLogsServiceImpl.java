package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.UserCollectionLogsDao;
import com.xz.video.entity.UserCollectionLogsEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.service.UserCollectionLogsService;
import com.xz.video.service.UserService;
import com.xz.video.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xz.video.entity.vo.CollectionLogVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户收藏模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("userCollectionLogsService")
public class UserCollectionLogsServiceImpl extends ServiceImpl<UserCollectionLogsDao, UserCollectionLogsEntity> implements UserCollectionLogsService {

    @Autowired
    VideoService videoService;

    @Autowired
    UserService userService;

    /**
     * 增加用户的收藏记录
     * @param collectionLogsEntity 收藏记录类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserCollectionLog(UserCollectionLogsEntity collectionLogsEntity) {
        this.baseMapper.insert(collectionLogsEntity);
        VideoEntity videoEntity = videoService.getOne(new QueryWrapper<VideoEntity>().eq("id", collectionLogsEntity.getVideoId()));
        videoEntity.setCollectionCount(videoEntity.getCollectionCount() + 1);
        videoService.updateById(videoEntity);
        UserEntity user = userService.getById(collectionLogsEntity.getUserId());
        user.setCollectionVideoCount(user.getCollectionVideoCount() + 1);
        userService.updateById(user);
    }

    /**
     * 得到用户的所有收藏
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 集合列表
     */
    @Override
    public Map<String,Object> getAllUserCollectionLogs(long page, long limit, String userId) {
        List<CollectionLogVo> list  = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        Page<UserCollectionLogsEntity> selectPage = new Page<>(page,limit);
        IPage<UserCollectionLogsEntity> userCollectionLogPage = this.baseMapper.selectPage(selectPage,new QueryWrapper<UserCollectionLogsEntity>().eq("user_id", userId).orderByDesc("gmt_modified"));
        long count = userCollectionLogPage.getTotal();
        //可以优化使用数据库批量查找
        for (UserCollectionLogsEntity model:userCollectionLogPage.getRecords()) {
            CollectionLogVo collectionLogVo = new CollectionLogVo();
            VideoEntity video = videoService.getOne(new QueryWrapper<VideoEntity>().eq("id", model.getVideoId()).select("id","title","cover", "description", "view_count"));
            collectionLogVo.setCover(video.getCover());
            collectionLogVo.setDescription(video.getDescription());
            collectionLogVo.setTitle(video.getTitle());
            collectionLogVo.setViewCount(video.getViewCount());
            collectionLogVo.setVideoId(video.getId());
            collectionLogVo.setTime(model.getGmtModified());
            list.add(collectionLogVo);
        }
        map.put("count",count);
        map.put("list",list);
        return map;
    }


    /**
     * 删除用户收藏记录
     * @param collectionLogsEntity 收藏记录类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delUserCollectionLog(UserCollectionLogsEntity collectionLogsEntity) {
        this.baseMapper.delete(new QueryWrapper<UserCollectionLogsEntity>().and(e -> e.eq("user_id", collectionLogsEntity.getUserId()).eq("video_id", collectionLogsEntity.getVideoId())));
        VideoEntity videoEntity = videoService.getOne(new QueryWrapper<VideoEntity>().eq("id", collectionLogsEntity.getVideoId()));
        if (videoEntity.getCollectionCount() > 0) {
            videoEntity.setCollectionCount(videoEntity.getCollectionCount() - 1);
        }
        videoService.updateById(videoEntity);
        UserEntity user = userService.getById(collectionLogsEntity.getUserId());
        if (user.getCollectionVideoCount() > 0) {
            user.setCollectionVideoCount(user.getCollectionVideoCount() - 1);
            userService.updateById(user);
        }
    }

    /**
     * 检验用户是否收藏
     * @param collectionLogsEntity 收藏类
     */
    @Override
    public boolean checkCollection(UserCollectionLogsEntity collectionLogsEntity) {
        QueryWrapper<UserCollectionLogsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(e -> e.eq("user_id", collectionLogsEntity.getUserId()).eq("video_id", collectionLogsEntity.getVideoId()));
        UserCollectionLogsEntity userCollectionLogs = this.baseMapper.selectOne(queryWrapper);
        if (userCollectionLogs == null) {
            return false;
        }
        return true;
    }
}
