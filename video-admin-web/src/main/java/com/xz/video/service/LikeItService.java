package com.xz.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.LikeItEntity;

/**
 * <p>
 * 点赞模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface LikeItService extends IService<LikeItEntity> {

    /**
     * 好评
     * @param likeItEntity 点赞类
     */
    void support(LikeItEntity likeItEntity);

    /**
     * 差评
     * @param likeItEntity 点赞类
     */
    void cancelSupport(LikeItEntity likeItEntity);
}
