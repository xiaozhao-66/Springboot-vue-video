package com.xz.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.UserCollectionLogsEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.CollectionLogVo;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 用户收藏模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface UserCollectionLogsService extends IService<UserCollectionLogsEntity> {

    /**
     * 增加用户的收藏记录
     * @param collectionLogsEntity 收藏记录类
     */
    void addUserCollectionLog(UserCollectionLogsEntity collectionLogsEntity);

    /**
     * 得到用户的所有收藏
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return 集合列表
     */
    Map<String,Object> getAllUserCollectionLogs(long page, long limit, String userId);

    /**
     * 删除用户收藏记录
     * @param collectionLogsEntity 收藏记录类
     */
    void delUserCollectionLog(UserCollectionLogsEntity collectionLogsEntity);

    /**
     * 检验用户是否收藏
     * @param collectionLogsEntity 收藏类
     */
    boolean checkCollection(UserCollectionLogsEntity collectionLogsEntity);
}
