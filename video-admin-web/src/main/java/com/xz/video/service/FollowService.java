package com.xz.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.FollowEntity;
import com.xz.video.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关注用户模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface FollowService extends IService<FollowEntity> {

    /**
     * 根据用户id得到所有关注
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return map集合
     */
    Map<String,Object> getAllFollow(long page,long limit,String userId);

    /**
     * 添加关注
     * @param followEntity 关注实体类
     */
    void addFollow(FollowEntity followEntity);

    /**
     * 取消关注
     * @param followEntity 关注实体类
     */
    void cancelFollow(FollowEntity followEntity);


    /**
     * 查看当前用户是否被关注
     * @param followEntity 关注用户类
     * @return boolean类型变量
     */
    boolean checkFollow(FollowEntity followEntity);
}
