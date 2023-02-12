package com.xz.video.common.listener;

import com.rabbitmq.client.Channel;
import com.xz.video.entity.OrderEntity;
import com.xz.video.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 定时关单，监听死信队列
 * @author  xiaozhao
 * @date  2022/12/3 17:24
 */
@Slf4j
@RabbitListener(queues = "order.release.order.queue")
@Component
public class OrderCloseListener {

    @Autowired
    OrderService orderService;

    @RabbitHandler
    public void handleOrderRelease(OrderEntity order, Message message, Channel channel) throws IOException {
        log.info("订单解锁，订单号：" + order.getOrderNo());
        try {
            /*
            可以做一些其他的处理，这里暂时只输出当前订单的支付状态，正常情况下应该
            根据订单的支付状态去更新库存和优惠券
             */
            OrderEntity orderEntity = orderService.getById(order.getId());
            log.info("支付状态"+orderEntity.getStatus());
            // 手动删除消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 解锁失败 将消息重新放回队列，让别人消费
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

}