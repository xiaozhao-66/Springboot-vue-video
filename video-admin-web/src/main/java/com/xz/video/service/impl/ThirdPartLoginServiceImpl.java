package com.xz.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xz.video.dao.ThirdPartLoginDao;
import com.xz.video.entity.CategoryEntity;
import com.xz.video.entity.ThirdPartLoginEntity;

import com.xz.video.service.ThirdPartLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 第三方登录管理模块（暂未开发）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service
public class ThirdPartLoginServiceImpl  extends ServiceImpl<ThirdPartLoginDao, ThirdPartLoginEntity> implements ThirdPartLoginService {


}