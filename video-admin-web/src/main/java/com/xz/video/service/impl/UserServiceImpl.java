package com.xz.video.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.UserDao;
import com.xz.video.entity.UserEntity;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.UserVo;
import com.xz.video.queryParams.UserQueryParams;
import com.xz.video.service.UserService;
import com.xz.video.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    //添加懒加载防止循环依赖
    @Lazy
    VideoService videoService;

    /**
     * 根据用户名称得到当前用户
     * @param username 用户名
     * @return 用户类
     */
    @Override
    public UserEntity getUserByName(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().eq("username", username);
        return this.getOne(queryWrapper);
    }

    /**
     * 修改用户
     * @param userVo 用户类
     */
    @Override
    public Integer setting(UserVo userVo) {
        if((userVo.getRetypePassword()!=null&&StringUtils.isEmpty(userVo.getPassword()))||
                (userVo.getRetypePassword()==null&&!StringUtils.isEmpty(userVo.getPassword()))||
                (userVo.getRetypePassword()!=null&& StringUtils.isEmpty(userVo.getPassword())&&StringUtils.equals(userVo.getPassword(),userVo.getRetypePassword()))){
            return 1;
        }
        UserEntity userEntity = this.baseMapper.selectById(userVo.getId());
        if(userVo.getRetypePassword()==null&& StringUtils.isEmpty(userVo.getPassword())){
            String password = userEntity.getPassword();
            BeanUtils.copyProperties(userVo,userEntity);
            userEntity.setPassword(password);
        }else{
            String password = SecureUtil.md5(userVo.getPassword());
            BeanUtils.copyProperties(userVo,userEntity);
            userEntity.setPassword(password);
        }
        this.baseMapper.updateById(userEntity);
        String userId = userEntity.getId();
        String username = userEntity.getUsername();
        String avatar = userEntity.getAvatar();
        List<VideoEntity> videoEntities = videoService.list(new QueryWrapper<VideoEntity>().eq("user_id", userId));
        for (VideoEntity entity : videoEntities) {
            entity.setUsername(username);
            entity.setAvatar(avatar);
        }
        videoService.updateBatchById(videoEntities);
        return 0;
    }

    /**
     * 得到所有的用户
     * @param page 分页
     * @param limit 分页大小
     * @param userQueryParams 查询条件
     * @return 用户集合
     */
    @Override
    public IPage<UserEntity> getAllUser(long page, long limit, UserQueryParams userQueryParams) {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        //创建page对象
        Page<UserEntity> UserPage = new Page<>(page, limit);
        String username = userQueryParams.getUsername();
        String begin = userQueryParams.getBegin();
        String end = userQueryParams.getEnd();
        if (!StringUtils.isEmpty(username)) {
            queryWrapper.like("username", username);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        IPage<UserEntity> pageInfo = this.baseMapper.selectPage(UserPage, queryWrapper);
        return pageInfo;
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    @Override
    public void removeAll(String id) {
        this.baseMapper.deleteById(id);
    }
}
