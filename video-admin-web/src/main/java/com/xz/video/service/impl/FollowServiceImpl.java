package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.FollowDao;
import com.xz.video.entity.FanEntity;
import com.xz.video.entity.FollowEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.service.FanService;
import com.xz.video.service.FollowService;
import com.xz.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 关注用户模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("followServiceImpl")
public class FollowServiceImpl extends ServiceImpl<FollowDao, FollowEntity> implements FollowService {
    @Autowired
    UserService userService;

    @Autowired
    FanService fanService;

    /**
     * 根据用户id得到所有关注
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return map集合
     */
    @Override
    public Map<String,Object> getAllFollow(long page, long limit, String userId) {
        Page<FollowEntity> selectPage = new Page<>(page, limit);
        Map<String,Object> map = new HashMap<>();
        IPage<FollowEntity> fanEntities = this.baseMapper.selectPage(selectPage,new QueryWrapper<FollowEntity>().eq("user_id", userId));
        long count = fanEntities.getTotal();
        List<UserEntity> userList=null;
        if(count>0){
            List<String> ids = fanEntities.getRecords().stream().map(FollowEntity::getFollowUserId).collect(Collectors.toList());
            userList = userService.listByIds(ids);
        }
        map.put("count",count);
        map.put("list",userList);
        return map;
    }

    /**
     * 添加关注
     * @param followEntity 关注实体类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addFollow(FollowEntity followEntity) {
        this.baseMapper.insert(followEntity);
        FanEntity fanEntity = new FanEntity();
        fanEntity.setUserId(followEntity.getFollowUserId());
        fanEntity.setFanUserId(followEntity.getUserId());
        fanService.save(fanEntity);
        UserEntity user = userService.getById(followEntity.getUserId());
        UserEntity follower = userService.getById(followEntity.getFollowUserId());
        user.setFollowCount(user.getFollowCount() + 1);
        follower.setFanCount(follower.getFanCount() + 1);
        userService.updateById(user);
        userService.updateById(follower);
    }

    /**
     * 取消关注
     * @param followEntity 关注实体类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelFollow(FollowEntity followEntity) {
        QueryWrapper<FollowEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(e -> e.eq("user_id", followEntity.getUserId()).eq("follow_user_id", followEntity.getFollowUserId()));
        this.baseMapper.delete(queryWrapper);
        QueryWrapper<FanEntity> fanQueryWrapper = new QueryWrapper<>();
        fanQueryWrapper.and(e -> e.eq("user_id", followEntity.getFollowUserId()).eq("fan_user_id", followEntity.getUserId()));
        fanService.remove(fanQueryWrapper);

        UserEntity user = userService.getById(followEntity.getUserId());
        UserEntity follower = userService.getById(followEntity.getFollowUserId());
        if (user.getFollowCount() > 0 && follower.getFanCount() > 0) {
            user.setFollowCount(user.getFollowCount() - 1);
            follower.setFanCount(follower.getFanCount() - 1);
        }
        userService.updateById(user);
        userService.updateById(follower);
    }

    /**
     * 查看当前用户是否被关注
     * @param followEntity 关注用户类
     * @return boolean类型变量
     */
    @Override
    public boolean checkFollow(FollowEntity followEntity) {
        QueryWrapper<FollowEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(e -> e.eq("user_id", followEntity.getUserId()).eq("follow_user_id", followEntity.getFollowUserId()));
        FollowEntity follow = this.baseMapper.selectOne(queryWrapper);
        return follow != null;
    }
}
