package com.xz.video.controller.front;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.MessageEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.UserMessageCountEntity;
import com.xz.video.entity.vo.MessageVo;
import com.xz.video.entity.vo.UserLastMsgVo;
import com.xz.video.service.MessageService;
import com.xz.video.service.UserMessageCountService;
import com.xz.video.service.UserService;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 消息模块（需要改进）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/message")
public class MessageFrontController {


    @Autowired
    MessageService messageService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserService userService;

    @Autowired
    UserMessageCountService userMessageCountService;

    /**
     * 得到返回私信用户的信息
     * @param userId 用户id
     * @return 私信集合
     */
    @RequestMapping("getUserListByUserId/{userId}")
    public Result getUserListByUserId(@PathVariable String userId) {
        Result<List<UserLastMsgVo>> result = new Result<>();
        List<UserLastMsgVo> userListByUserId = messageService.getUserListByUserId(userId);
        return result.ok(userListByUserId);
    }

    /**
     * 增加用户的消息记录
     * @param userMessageCountEntity 用户消息实体类
     */
    @RequestMapping("addUserMessageCount")
    public Result addUserMessageCount(@RequestBody UserMessageCountEntity userMessageCountEntity){
        userMessageCountService.addUserMessageCount(userMessageCountEntity);
        return new Result().ok();
    }

    /**
     * 得到用户未回复的消息
     * @param userId 用户id
     * @return 用户未回复的私信消息和回复消息数组集合
     */
    @RequestMapping("getUserNoReplyCount/{userId}")
    public Result getUserNoReplyCount(@PathVariable String userId) {
        Result<int[]> result = new Result<>();
        int[] count = userMessageCountService.getUserNoReplyCount(userId);
        return result.ok(count);
    }

    /**
     * 保存私信消息到数据库
     * @param messageVo 消息vo
     */
    @RequestMapping("save")
    public Result save(@RequestBody MessageVo messageVo) {
        Result result = new Result<>();
        messageService.saveMessage(messageVo);
        return result.ok();
    }


    /**
     * 得到当前用户与聊天用户的所有消息
     * @param fromUserId 消息传递者
     * @param toUserId 消息到达者
     * @return 消息集合
     */
    @RequestMapping("getAllMessage/{fromUserId}/{toUserId}")
    public Result getAllMessage(@PathVariable String fromUserId, @PathVariable String toUserId) {
        Result<List<MessageVo>> result = new Result<>();
        List<MessageVo> list =  messageService.getAllMessage(fromUserId,toUserId);
        return result.ok(list);
    }
}
