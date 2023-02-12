package com.xz.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.UserMessageCountEntity;

/**
 * <p>
 * 用户消息记录模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface UserMessageCountService extends IService<UserMessageCountEntity> {

    /**
     * 得到用户未回复的消息
     * @param userId 用户id
     * @return 用户未回复的私信消息和回复消息数组集合
     */
    int[] getUserNoReplyCount(String userId);

    /**
     * 增加用户的消息记录
     * @param userMessageCountEntity 用户消息实体类
     */
    void addUserMessageCount(UserMessageCountEntity userMessageCountEntity);
}
