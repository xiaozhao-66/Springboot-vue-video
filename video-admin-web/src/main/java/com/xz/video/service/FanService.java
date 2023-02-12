package com.xz.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.FanEntity;
import java.util.Map;

/**
 * <p>
 * 粉丝模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface FanService extends IService<FanEntity> {

    /**
     * 根据用户id得到所有粉丝
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return map集合
     */
    Map<String, Object> getAllFan(long page, long limit, String userId);
}
