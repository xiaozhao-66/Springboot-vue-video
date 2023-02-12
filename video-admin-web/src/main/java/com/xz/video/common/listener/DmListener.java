package com.xz.video.common.listener;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.Channel;
import com.xz.video.common.client.VideoClient;
import com.xz.video.entity.OrderEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.service.UserService;
import io.renren.common.entity.dm.DmMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 邮件发送监听
 *
 */
@Slf4j
@RabbitListener(queues = "dm.release.queue")
@Component
public class DmListener {

    @Autowired
    UserService userService;

    @Autowired
    VideoClient videoClient;

    @RabbitHandler
    public void handleOrderRelease(OrderEntity order, Message message, Channel channel) throws IOException {
        log.info("订单解锁，订单号：" + order.getOrderNo());
        System.out.println("******************订单解锁，订单号：" + order.getOrderNo()+"********************");
        try {
            //得到当前用户的信息
            DmMessage dmMessage  = new DmMessage();
            UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("id", order.getMemberId()));
            String content = order.getOrderNo();
            dmMessage.setToAddress(userEntity.getEmail());
            dmMessage.setContent("您的订单已完成;当前订单号为"+content);
            //发送邮件
            videoClient.sendDm(dmMessage);
            // 手动删除消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 解锁失败 将消息重新放回队列，让别人消费
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
