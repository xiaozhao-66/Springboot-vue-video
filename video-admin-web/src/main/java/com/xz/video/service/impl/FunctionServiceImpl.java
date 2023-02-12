package com.xz.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.CommentDao;
import com.xz.video.dao.FunctionDao;
import com.xz.video.entity.CommentEntity;
import com.xz.video.entity.FunctionEntity;
import com.xz.video.service.FunctionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 功能模块（暂未开发）
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@Service("functionService")
public class FunctionServiceImpl  extends ServiceImpl<FunctionDao, FunctionEntity> implements FunctionService {
}
