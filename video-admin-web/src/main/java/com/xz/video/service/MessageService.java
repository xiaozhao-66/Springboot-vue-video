package com.xz.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.MessageEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.UserMessageCountEntity;
import com.xz.video.entity.vo.MessageVo;
import com.xz.video.entity.vo.UserLastMsgVo;

import java.util.List;

/**
 * <p>
 * 消息模块（需要改进）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface MessageService extends IService<MessageEntity> {

    /**
     * 得到返回私信用户的信息
     * @param userId 用户id
     * @return 私信集合
     */
    List<UserLastMsgVo> getUserListByUserId(String userId);

    /**
     * 保存私信消息到数据库
     * @param messageVo 消息vo
     */
    void saveMessage(MessageVo messageVo);

    /**
     * 得到当前用户与聊天用户的所有消息
     * @param fromUserId 消息传递者
     * @param toUserId 消息到达者
     * @return 消息集合
     */
    List<MessageVo> getAllMessage(String fromUserId, String toUserId);
}
