package com.xz.video.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.dao.MessageDao;
import com.xz.video.entity.MessageEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.UserMessageCountEntity;
import com.xz.video.entity.vo.MessageVo;
import com.xz.video.entity.vo.UserLastMsgVo;
import com.xz.video.service.MessageService;
import com.xz.video.service.UserMessageCountService;
import com.xz.video.service.UserService;
import io.renren.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 消息模块（需要改进）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserMessageCountService userMessageCountService;

    /**
     * 得到返回私信用户的信息
     * @param userId 用户id
     * @return 私信集合
     */
    @Override
    public List<UserLastMsgVo> getUserListByUserId(String userId) {
        List<UserLastMsgVo> result = new ArrayList<>();
        List<UserMessageCountEntity> list = userMessageCountService.list(new QueryWrapper<UserMessageCountEntity>().eq("current_user_id", userId));

        for (UserMessageCountEntity model : list) {
            UserLastMsgVo userLastMsgVo = new UserLastMsgVo();
            BeanUtils.copyProperties(model, userLastMsgVo);
            UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("id", model.getFromUserId()));
            userLastMsgVo.setUsername(user.getUsername());
            userLastMsgVo.setAvatar(user.getAvatar());
            result.add(userLastMsgVo);
        }
        return result;
    }

    /**
     * 保存私信消息到数据库
     * @param messageVo 消息vo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveMessage(MessageVo messageVo) {
        if(StringUtils.isEmpty(messageVo.getFromUserId())||StringUtils.isEmpty(messageVo.getToUserId())){
            return;
        }
        String key = "MSG:" + messageVo.getFromUserId() + ":" + messageVo.getToUserId();
        String value = JSON.toJSONString(messageVo);
        redisUtils.zAdd(key, value, System.currentTimeMillis());

        //用户未收到的私信消息+1
        UserMessageCountEntity userMessageCount = userMessageCountService.getOne(new QueryWrapper<UserMessageCountEntity>().and(e -> e.eq("from_user_id", messageVo.getFromUserId()).eq("current_user_id", messageVo.getToUserId())));
        if (userMessageCount == null) {
            UserMessageCountEntity userMessageCountEntity = new UserMessageCountEntity();
            userMessageCountEntity.setCurrentUserId(messageVo.getToUserId());
            userMessageCountEntity.setFromUserId(messageVo.getFromUserId());
            userMessageCountEntity.setCount(1);
            userMessageCountEntity.setContent(messageVo.getData());
            userMessageCountService.save(userMessageCountEntity);
        } else {
            userMessageCount.setCount(userMessageCount.getCount() + 1);
            userMessageCount.setContent(messageVo.getData());
            userMessageCountService.updateById(userMessageCount);
        }
    }

    /**
     * 得到当前用户与聊天用户的所有消息
     * @param fromUserId 消息传递者
     * @param toUserId 消息到达者
     * @return 消息集合
     */
    @Override
    public List<MessageVo> getAllMessage(String fromUserId, String toUserId) {
        //修改用户未读私信消息为0
        UserMessageCountEntity userMessageCount = userMessageCountService.getOne(new QueryWrapper<UserMessageCountEntity>()
                .and(e -> e.eq("from_user_id", toUserId).eq("current_user_id", fromUserId)));
        if(userMessageCount!=null){
            userMessageCount.setCount(0);
            userMessageCountService.updateById(userMessageCount);
        }
        List<MessageVo> list = new ArrayList<>();

        String key1 = "MSG:" + fromUserId + ":" + toUserId;
        String key2 = "MSG:" + toUserId + ":" + fromUserId;

        if (redisUtils.hasKey(key1) || redisUtils.hasKey(key2)) {
            Set<ZSetOperations.TypedTuple<String>> typedTuples1 = redisUtils.zRangeWithScores(key1, 0, -1);
            Set<ZSetOperations.TypedTuple<String>> typedTuples2 = redisUtils.zRangeWithScores(key2, 0, -1);

            for (ZSetOperations.TypedTuple<String> tuple : typedTuples1) {
                String messageVo = tuple.getValue();
                long score = tuple.getScore().longValue();
                MessageVo msg1 = JSON.parseObject(messageVo, MessageVo.class);
                msg1.setDateTime(DateUtil.date(score));
                list.add(msg1);
            }

            for (ZSetOperations.TypedTuple<String> tuple : typedTuples2) {
                String messageVo = tuple.getValue();
                long score = tuple.getScore().longValue();
                MessageVo msg2 = JSON.parseObject(messageVo, MessageVo.class);
                msg2.setDateTime(DateUtil.date(score));
                list.add(msg2);
            }

        } else {
            List<MessageEntity> listBySql1 = this.baseMapper.selectList(new QueryWrapper<MessageEntity>().and(e -> e.eq("send_user_id", fromUserId).eq("accept_user_id", toUserId)));
            List<MessageEntity> listBySql2 = this.baseMapper.selectList(new QueryWrapper<MessageEntity>().and(e -> e.eq("send_user_id", toUserId).eq("accept_user_id", fromUserId)));

            for (MessageEntity entity : listBySql1) {
                MessageVo messageVo = new MessageVo();
                messageVo.setFromUserId(entity.getSendUserId());
                messageVo.setToUserId(entity.getAcceptUserId());
                messageVo.setData(entity.getData());
                messageVo.setTime(entity.getTime());
                messageVo.setDateTime(entity.getGmtModified());
                list.add(messageVo);
            }

            for (MessageEntity entity : listBySql2) {
                MessageVo messageVo = new MessageVo();
                messageVo.setFromUserId(entity.getSendUserId());
                messageVo.setToUserId(entity.getAcceptUserId());
                messageVo.setData(entity.getData());
                messageVo.setTime(entity.getTime());
                messageVo.setDateTime(entity.getGmtModified());
                list.add(messageVo);
            }
        }
        list.sort((o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        return list;
    }
}
