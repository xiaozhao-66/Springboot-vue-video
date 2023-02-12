package com.xz.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.UserViewLogsEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.VideoWithViewLogsVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户浏览记录模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface UserViewLogsService extends IService<UserViewLogsEntity> {

    /**
     * 增加用户的浏览记录
     * @param userViewLogsEntity 用户浏览记录类
     */
    void addUserViewLogs(UserViewLogsEntity userViewLogsEntity);

    /**
     * 得到用户的所有浏览记录
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 返回浏览记录集合
     */
    Map<String,Object> getAllUserViewLogs(long page, long limit, String userId);

    /**
     * 删除用户的浏览记录
     * @param userId 用户id
     * @param videoId 视频id
     */
    void delOneRecord(String userId, String videoId);
}
