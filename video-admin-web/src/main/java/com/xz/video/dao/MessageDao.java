package com.xz.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.video.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao extends BaseMapper<MessageEntity> {
}
