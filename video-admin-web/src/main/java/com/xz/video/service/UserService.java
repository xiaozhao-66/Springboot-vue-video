package com.xz.video.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.vo.UserVo;
import com.xz.video.queryParams.UserQueryParams;

/**
 * <p>
 * 用户模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface UserService  extends IService<UserEntity> {

    /**
     * 根据用户名称得到当前用户
     * @param username 用户名
     * @return 用户类
     */
    UserEntity getUserByName(String username);

    /**
     * 修改用户
     * @param userVo 用户类
     */
    Integer setting(UserVo userVo);

    /**
     * 得到所有的用户
     * @param page 分页
     * @param limit 分页大小
     * @param userQueryParams 查询条件
     * @return 用户集合
     */
    IPage<UserEntity> getAllUser(long page, long limit, UserQueryParams userQueryParams);

    /**
     * 删除用户
     * @param id 用户id
     */
    void removeAll(String id);
}
