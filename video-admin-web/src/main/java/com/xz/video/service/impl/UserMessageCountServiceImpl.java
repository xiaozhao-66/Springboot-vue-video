package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.UserCollectionLogsDao;
import com.xz.video.dao.UserMessageCountDao;
import com.xz.video.entity.UserCollectionLogsEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.UserMessageCountEntity;
import com.xz.video.service.UserMessageCountService;
import com.xz.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户消息记录模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("userMessageCountService")
public class UserMessageCountServiceImpl extends ServiceImpl<UserMessageCountDao, UserMessageCountEntity> implements UserMessageCountService {

    @Autowired
    UserService userService;

    /**
     * 得到用户未回复的消息
     * @param userId 用户id
     * @return 用户未回复的私信消息和回复消息数组集合
     */
    @Override
    public int[] getUserNoReplyCount(String userId) {
        int[] count = new int[2];
        int sum = 0;
        List<UserMessageCountEntity> list = this.baseMapper.selectList(new QueryWrapper<UserMessageCountEntity>().eq("current_user_id", userId));
        for (UserMessageCountEntity e : list) {
            sum += e.getCount();
        }
        UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("id", userId).select("no_reply_comment_count"));
        count[0] = user.getNoReplyCommentCount();
        count[1] = sum;
        return count;
    }

    /**
     * 增加用户的消息记录
     * @param userMessageCountEntity 用户消息实体类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserMessageCount(UserMessageCountEntity userMessageCountEntity) {
          QueryWrapper<UserMessageCountEntity> queryWrapper = new QueryWrapper<>();
          queryWrapper.and(e->e.eq("current_user_id",userMessageCountEntity.getCurrentUserId()).eq("from_user_id",userMessageCountEntity.getFromUserId()));
          UserMessageCountEntity userMessageCount = this.baseMapper.selectOne(queryWrapper);
          if(userMessageCount==null){
              this.baseMapper.insert(userMessageCountEntity);
              UserMessageCountEntity model = new UserMessageCountEntity();
              model.setCurrentUserId(userMessageCountEntity.getFromUserId());
              model.setFromUserId(userMessageCountEntity.getCurrentUserId());
              this.baseMapper.insert(model);
          }
    }
}
