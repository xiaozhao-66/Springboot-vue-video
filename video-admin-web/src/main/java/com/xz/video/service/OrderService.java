package com.xz.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.OrderEntity;


/**
 * <p>
 * 订单模块（可以进一步开发）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface OrderService extends IService<OrderEntity> {

    /**
     * 增加一条订单
     * @param userId 用户id
     * @param videoId 视频id
     * @return 支付宝响应页面
     */
    String addOrder(String userId, String videoId);

    /**
     * 查看支付状态
     * @param userId 用户id
     * @param videoId 视频id
     */
    boolean payStatus(String userId, String videoId);
}