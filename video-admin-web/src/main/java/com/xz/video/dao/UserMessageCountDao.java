package com.xz.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.UserMessageCountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMessageCountDao extends BaseMapper<UserMessageCountEntity> {
}
