package com.xz.video.common.task;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.MessageEntity;
import com.xz.video.entity.UserViewLogsEntity;
import com.xz.video.entity.vo.MessageVo;
import com.xz.video.service.MessageService;
import com.xz.video.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * 消息定时任务类
 */
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@Slf4j
public class MessageTask {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    MessageService messageService;

    @Autowired
    WebSocketServer webSocketServer;

    @Async
    @Scheduled(fixedDelay = 1000)
    public void addMessage(){
        Set<String> keys = redisUtils.getListKey("MSG");

        for (String key:keys) {
            if(key!=null){
                Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtils.zReverseRangeWithScores(key, 0, -1);
                String[] split = key.split(":");
                String fromUserId = split[1];
                String toUserId = split[2];
                List<MessageEntity> messageEntityList = messageService.list(new QueryWrapper<MessageEntity>().and(e->e.eq("send_user_id",fromUserId).eq("accept_user_id",toUserId)));

                List<Long> messageTime = messageEntityList.stream().map(e->{
                    return Long.valueOf(e.getTime());
                }).collect(Collectors.toList());

                for (ZSetOperations.TypedTuple<String> tuple:typedTuples) {
                    String messageVo = tuple.getValue();
                    long score = tuple.getScore().longValue();
                    MessageVo msg = JSON.parseObject(messageVo,MessageVo.class);

                    if(!messageTime.contains(score)){
                        MessageEntity entity = new MessageEntity();
                        entity.setSendUserId(msg.getFromUserId());
                        entity.setAcceptUserId(msg.getToUserId());
                        entity.setGmtCreate(DateUtil.date(score));
                        entity.setGmtModified(DateUtil.date(score));
                        entity.setTime(String.valueOf(score));
                        entity.setData(msg.getData());
                        messageService.save(entity);
                    }
                }
            }
        }
    }
}
