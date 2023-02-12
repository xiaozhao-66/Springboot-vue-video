package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.dao.VideoDao;
import com.xz.video.entity.TagEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.VideoinfoTagRelationEntity;
import com.xz.video.entity.vo.VideoInfoWithTagVo;
import com.xz.video.queryParams.VideoBindingTagsQueryParams;
import com.xz.video.queryParams.VideoFrontQueryParams;
import com.xz.video.queryParams.VideoQueryParams;
import com.xz.video.service.TagService;
import com.xz.video.service.UserService;
import com.xz.video.service.VideoService;
import com.xz.video.service.VideoinfoTagRelationService;
import io.renren.common.exception.RenException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 视频模块
 * </p>
 *
 * @author 小赵
 * @since 2022-12-20
 */
@Service("videoService")
public class VideoServiceImpl extends ServiceImpl<VideoDao, VideoEntity> implements VideoService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoinfoTagRelationService videoinfoTagRelationService;

    @Autowired
    private TagService tagService;

    /**
     * 根据分页条件得到视频列表
     * @param page 分页
     * @param limit 分页大小
     * @param videoQueryParams 查询条件
     * @return 视频信息集合
     */
    @Override
    public IPage<VideoEntity> pageCondition(long page, long limit, VideoQueryParams videoQueryParams) {
        QueryWrapper<VideoEntity> queryWrapper = new QueryWrapper<>();
        //创建page对象
        Page<VideoEntity> videoPage = new Page<>(page, limit);
        String title = videoQueryParams.getTitle();
        String begin = videoQueryParams.getBegin();
        String end = videoQueryParams.getEnd();
        String categoryId = videoQueryParams.getCategoryId();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        if (!StringUtils.isEmpty(categoryId)) {
            queryWrapper.eq("category_id", categoryId);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        return this.baseMapper.selectPage(videoPage, queryWrapper);
    }

    /**
     * 保存视频信息
     * @param videoEntity 视频类
     * @return 视频id
     */
    @Override
    public String saveVideoInfo(VideoEntity videoEntity) {
        videoEntity.setBuyCount(0L);
        videoEntity.setVersion(0L);
        videoEntity.setViewCount(0L);
        int insert = this.baseMapper.insert(videoEntity);
        if (insert == 0) {
            //添加失败
            throw new RenException("添加失败");
        }
        return videoEntity.getId();
    }

    /**
     * 删除视频
     * @param id 视频id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeAll(String id) {
        VideoEntity videoEntity = this.baseMapper.selectById(id);
        UserEntity user = userService.getById(videoEntity.getUserId());
        if (user.getAddVideoCount() > 0) {
            user.setAddVideoCount(user.getAddVideoCount() - 1);
        }
        userService.updateById(user);
        this.baseMapper.deleteById(id);
    }


    /**
     * 获取受欢迎的视频
     * @return 视频集合
     */
    @Override
    public List<VideoEntity> getPopularVideos() throws InterruptedException {
        List<VideoEntity> result = new ArrayList<>(5);
        Set<ZSetOperations.TypedTuple<String>> videoViews = redisUtils.zReverseRangeWithScores("videoViews", 0, 4);
        for (ZSetOperations.TypedTuple<String> e : videoViews) {
            String videoId = e.getValue();
            VideoEntity entity = this.baseMapper.selectById(videoId);
            result.add(entity);
        }
        return result;
    }

    /**
     * 前台视频列表显示
     * @param page 分页
     * @param limit 分页大小
     * @param videoFrontQueryParams 查询条件
     * @return  视频集合
     */
    @Override
    public IPage<VideoEntity> pageFrontCondition(long page, long limit, VideoFrontQueryParams videoFrontQueryParams) {
        QueryWrapper<VideoEntity> queryWrapper = new QueryWrapper<>();
        //创建page对象
        Page<VideoEntity> videoPage = new Page<>(page, limit);
        String categoryId = videoFrontQueryParams.getCategoryId();
        String searchInfo = videoFrontQueryParams.getSearchInfo();
        if (!StringUtils.isEmpty(categoryId)) {
            queryWrapper.eq("category_id", categoryId);
        }
        if (!StringUtils.isEmpty(searchInfo)) {
            queryWrapper.like("title", searchInfo);
        }
        //排序
        queryWrapper.eq("status", 1);
        queryWrapper.orderByDesc("gmt_create");
        return this.baseMapper.selectPage(videoPage, queryWrapper);
    }

    /**
     * 上传一条视频
     * @param videoBindingTagsQueryParams 视频实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(VideoBindingTagsQueryParams videoBindingTagsQueryParams) {
        VideoEntity videoEntity = new VideoEntity();
        BeanUtils.copyProperties(videoBindingTagsQueryParams, videoEntity);
        videoEntity.setBuyCount(0L);
        videoEntity.setVersion(0L);
        videoEntity.setViewCount(0L);
        videoEntity.setCollectionCount(0L);
        videoEntity.setSupportCount(0L);
        videoEntity.setUnsupportCount(0L);
        if (StringUtils.isEmpty(videoBindingTagsQueryParams.getUserId()) && StringUtils.isEmpty(videoBindingTagsQueryParams.getUsername())) {
            videoEntity.setStatus(1);
            //设置后端发布视频是管理员用户
            videoEntity.setUserId("1594985167723864065");
            videoEntity.setUsername("admin");
            videoEntity.setAvatar("https://img0.baidu.com/it/u=1378190466,3770230837&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=634");
        }
        this.baseMapper.insert(videoEntity);
        UserEntity user = userService.getById(videoEntity.getUserId());
        user.setAddVideoCount(user.getAddVideoCount() + 1);
        userService.updateById(user);

        //--添加标签---
        VideoinfoTagRelationEntity videoinfoTagRelationEntity = new VideoinfoTagRelationEntity();
        videoinfoTagRelationEntity.setVideoId(videoEntity.getId());

        String[] selectTags = videoBindingTagsQueryParams.getSelectTags();

        StringBuilder stringBuilder = new StringBuilder();
        for (String e:selectTags) {
            stringBuilder.append(e);
            stringBuilder.append(";");
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
        videoinfoTagRelationEntity.setTagsId(stringBuilder.toString());
        videoinfoTagRelationService.save(videoinfoTagRelationEntity);
    }

    /**
     * 得到有联系的视频
     * @param videoId 视频id
     * @return 视频集合
     */
    @Override
    public List<VideoEntity> getRelatedVideos(String videoId) {
        VideoEntity videoEntity = this.baseMapper.selectById(videoId);
        String categoryId = videoEntity.getCategoryId();
        QueryWrapper<VideoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        List<VideoEntity> list = this.baseMapper.selectList(queryWrapper);
        if (list.size() < 3) {
            return list;
        } else {
            return list.subList(0, 3);
        }
    }

    /**
     * 删除视频的播放地址
     * @param videoId 视频id
     */
    @Override
    public void deletePlayerUrl(String videoId) {
        VideoEntity entity = this.baseMapper.selectById(videoId);
        entity.setPlayerUrl("");
        this.baseMapper.updateById(entity);
    }

    /**
     * 更改视频的观看次数
     * @param videoEntity 视频实体类
     */
    @Override
    public void updateViewCount(VideoEntity videoEntity) {
        this.baseMapper.updateViewCount(videoEntity);
    }

    /**
     * 根据用户id得到当前用户上传的视频集合
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 视频集合
     */
    @Override
    public IPage<VideoEntity> getAllVideosWithUser(long page, long limit, String userId) {

        //创建page对象
        Page<VideoEntity> videoPage = new Page<>(page, limit);

        return this.baseMapper.selectPage(videoPage, new QueryWrapper<VideoEntity>().eq("user_id", userId).orderByDesc("gmt_modified"));
    }

    @Override
    public VideoInfoWithTagVo getVideoInfo(String videoId) {

        VideoInfoWithTagVo videoInfoWithTagVo = new VideoInfoWithTagVo();
        VideoEntity videoEntity = this.baseMapper.selectById(videoId);
        BeanUtils.copyProperties(videoEntity,videoInfoWithTagVo);
        VideoinfoTagRelationEntity videoinfoTagRelation = videoinfoTagRelationService.getOne(new QueryWrapper<VideoinfoTagRelationEntity>().eq("video_id", videoId));

        String[] tagIds = StringUtils.trim(videoinfoTagRelation.getTagsId()).split(";");

        videoInfoWithTagVo.setSelectTags(tagIds);
        List<TagEntity> tags = tagService.listByIds(Arrays.asList(tagIds));
        videoInfoWithTagVo.setTags(tags);
        return videoInfoWithTagVo;
    }

    @Override
    public void updateVideoInfo(VideoBindingTagsQueryParams videoBindingTagsQueryParams) {
        VideoEntity video = new VideoEntity();
        BeanUtils.copyProperties(videoBindingTagsQueryParams, video);
        this.baseMapper.updateById(video);

        //----------------
        VideoinfoTagRelationEntity videoinfoTagRelation = videoinfoTagRelationService.getOne(new QueryWrapper<VideoinfoTagRelationEntity>().eq("video_id", video.getId()));
        StringBuilder stringBuilder = new StringBuilder();
        for (String e:videoBindingTagsQueryParams.getSelectTags()) {
            stringBuilder.append(e);
            stringBuilder.append(";");
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
        videoinfoTagRelation.setTagsId(stringBuilder.toString());
        videoinfoTagRelationService.updateById(videoinfoTagRelation);
    }
}
