package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.FanDao;
import com.xz.video.dao.FunctionDao;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.video.entity.FanEntity;
import com.xz.video.entity.FunctionEntity;
import com.xz.video.entity.UserEntity;

import com.xz.video.service.FanService;
import com.xz.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 粉丝模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("fanServiceImpl")
public class FanServiceImpl extends ServiceImpl<FanDao, FanEntity> implements FanService {

    @Autowired
    UserService userService;

    /**
     * 根据用户id得到所有粉丝
     * @param page 分页
     * @param limit 分页大小
     * @param userId 用户id
     * @return map集合
     */
    @Override
    public Map<String, Object> getAllFan(long page, long limit, String userId) {
        Page<FanEntity> selectPage = new Page<>(page, limit);
        Map<String,Object> map = new HashMap<>();
        IPage<FanEntity> fanEntities = this.baseMapper.selectPage(selectPage,new QueryWrapper<FanEntity>().eq("user_id", userId));
        long count = fanEntities.getTotal();
        List<UserEntity> userList= null;
        if(count>0){
            List<String> ids = fanEntities.getRecords().stream().map(FanEntity::getFanUserId).collect(Collectors.toList());
            userList=userService.listByIds(ids);
        }
        map.put("count",count);
        map.put("list",userList);
        return map;
    }
}
