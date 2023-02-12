package com.xz.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.video.entity.VideoEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface VideoDao extends BaseMapper<VideoEntity> {
    void updateViewCount(VideoEntity entity);
}
