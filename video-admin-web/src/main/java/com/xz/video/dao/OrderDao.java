package com.xz.video.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.video.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-11-18
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}