package com.xz.video.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.entity.OrderEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.service.OrderService;
import com.xz.video.service.VideoService;
import io.renren.common.utils.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * 订单模块（可以进一步开发）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/order")
public class OrderFrontController {

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    VideoService videoService;

    /**
     * 增加一条订单
     * @param userId 用户id
     * @param videoId 视频id
     * @return 支付宝响应页面
     */
    @RequestMapping(value = "addOrder/{userId}/{videoId}",produces="text/html")
    public String addOrder(@PathVariable String userId, @PathVariable String videoId){
        return orderService.addOrder(userId, videoId);
    }

    /**
     * 查看支付状态
     * @param userId 用户id
     * @param videoId 视频id
     */
    @RequestMapping("payStatus/{userId}/{videoId}")
    public Result payStatus(@PathVariable String userId, @PathVariable String videoId){
        boolean flag = orderService.payStatus(userId,videoId);
        if(flag){
            return new Result().ok();
        }else {
            return new Result().error("未支付");
        }
    }

}
