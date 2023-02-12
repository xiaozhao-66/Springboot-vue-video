package com.xz.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.video.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-08-08
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
