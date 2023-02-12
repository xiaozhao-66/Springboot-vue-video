package com.xz.video.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.alipay.AliPayUtils;
import com.xz.video.dao.OrderDao;
import com.xz.video.entity.CategoryEntity;
import com.xz.video.entity.OrderEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.OrderVo;
import com.xz.video.service.OrderService;
import com.xz.video.service.UserService;
import com.xz.video.service.VideoService;
import io.renren.common.exception.RenException;
import io.renren.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单模块（可以进一步开发）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;

    @Autowired
    AliPayUtils aliPayUtils;



    /**
     * 增加一条订单
     * @param userId 用户id
     * @param videoId 视频id
     * @return 支付宝响应页面
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addOrder(String userId, String videoId) {

        //查找用户信息
        QueryWrapper<UserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("id",userId);
        UserEntity user = userService.getOne(userWrapper);

        //查找视频信息
        QueryWrapper<VideoEntity> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("id",videoId);
        VideoEntity video = videoService.getOne(videoWrapper);
        OrderVo orderVo = new OrderVo();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(RandomUtil.randomString(19));
        orderEntity.setVideoId(videoId);
        orderEntity.setVideoTitle(video.getTitle());
        orderEntity.setPrice(video.getPrice());
        orderEntity.setMemberId(userId);
        orderEntity.setUsername(user.getUsername());
        orderEntity.setMobile(user.getMobile());
        orderEntity.setPayType(2);
        orderEntity.setStatus(0);
        this.baseMapper.insert(orderEntity);
        orderVo.setOut_trade_no(orderEntity.getOrderNo());
        orderVo.setTotal_amount(String.valueOf(orderEntity.getPrice()));
        orderVo.setSubject(orderEntity.getVideoTitle());


        String payHtml="";
        try {
            payHtml= aliPayUtils.pay(orderVo);
        }catch (AlipayApiException e){
            throw new RenException("支付异常");
        }
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", orderEntity);
        return payHtml;
    }

    /**
     * 查看支付状态
     * @param userId 用户id
     * @param videoId 视频id
     */
    @Override
    public boolean payStatus(String userId, String videoId) {
        VideoEntity video = videoService.getById(videoId);
        if(video.getPrice().compareTo(new BigDecimal("0.00"))==0){
            return true;
        }
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",userId);
        queryWrapper.eq("video_id",videoId);
        List<OrderEntity> list = this.baseMapper.selectList(queryWrapper);

        if(list==null||list.isEmpty()){
           return false;
        }else{
            for (OrderEntity order:list) {
                if(order.getStatus()==1){
                    return true;
                }
            }
        }
        return false;
    }
}