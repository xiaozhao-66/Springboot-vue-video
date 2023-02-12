package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.LikeItDao;
import com.xz.video.dao.MessageDao;
import com.xz.video.entity.LikeItEntity;
import com.xz.video.entity.MessageEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.service.LikeItService;
import com.xz.video.service.MessageService;
import com.xz.video.service.VideoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 点赞模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("likeItService")
public class LikeItServiceImpl extends ServiceImpl<LikeItDao, LikeItEntity> implements LikeItService {

    @Autowired
    VideoService videoService;


    /**
     * 好评
     * @param likeItEntity 点赞类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void support(LikeItEntity likeItEntity) {

        LikeItEntity model = this.baseMapper.selectOne(new QueryWrapper<LikeItEntity>().and(e -> e.eq("video_id", likeItEntity.getVideoId()).eq("user_id", likeItEntity.getUserId())));
        VideoEntity video = videoService.getOne(new QueryWrapper<VideoEntity>().eq("id", likeItEntity.getVideoId()));

        if(model==null){
            this.baseMapper.insert(likeItEntity);
            if(likeItEntity.getType()==1){
                //支持
                video.setSupportCount(video.getSupportCount()+1);
            }else {
                //不支持
                video.setUnsupportCount(video.getUnsupportCount()+1);
            }
        }else{
            if(likeItEntity.getType()==1){
                //支持
                video.setSupportCount(video.getSupportCount()+1);
                if(video.getUnsupportCount()>0) {
                    video.setUnsupportCount(video.getUnsupportCount()-1);
                }
            }else {
                //不支持
                video.setUnsupportCount(video.getUnsupportCount()+1);
                if(video.getSupportCount()>0) {
                    video.setSupportCount(video.getSupportCount()-1);
                }
            }

            model.setType(likeItEntity.getType());
            this.baseMapper.updateById(model);

        }

        videoService.updateById(video);
    }

    /**
     * 差评
     * @param likeItEntity 点赞类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelSupport(LikeItEntity likeItEntity) {
        VideoEntity video = videoService.getOne(new QueryWrapper<VideoEntity>().eq("id", likeItEntity.getVideoId()));
        LikeItEntity model = this.baseMapper.selectOne(new QueryWrapper<LikeItEntity>().and(e -> e.eq("video_id", likeItEntity.getVideoId()).eq("user_id", likeItEntity.getUserId())));
        if(model!=null){
            if(likeItEntity.getType()==1){
                if(video.getSupportCount()>0) {
                    video.setSupportCount(video.getSupportCount()-1);
                }
            }else {
                if(video.getUnsupportCount()>0) {
                    video.setUnsupportCount(video.getUnsupportCount()-1);
                }
            }
            videoService.updateById(video);
            this.baseMapper.deleteById(model);
        }
    }
}
