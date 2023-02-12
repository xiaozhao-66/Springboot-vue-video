package com.xz.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.video.entity.CommentEntity;
import com.xz.video.queryParams.CommentQueryParams;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
public interface CommentService extends IService<CommentEntity> {

    /**
     * 根据视频ID得到所有的评论
     * @param videoId 视频id
     * @param page 一级评论分页
     * @param limit 一级评论分页大小
     * @param page2 二级评论
     * @param limit2 二级评论分页大小
     * @return 评论集合
     */
    IPage<CommentEntity>  getAllCommentByVideo(String videoId, long page, long limit, long page2, long limit2);

    /**
     * 根据条件得到所有的评论
     * @param page 当前页
     * @param limit 每页大小
     * @param commentQueryParams 查询条件
     * @return 评论集合
     */
    IPage<CommentEntity> getAllComment(long page, long limit, CommentQueryParams commentQueryParams);

    /**
     * 删除评论
     * @param commentId 评论id
     */
    void removeAll(String commentId);

    /**
     * 增加评论
     * @param commentEntity 评论实体
     */
    void addComment(CommentEntity commentEntity) throws IOException;

    /**
     * 得到用户所有未回复的评论
     * @param userId 用户id
     * @return 评论集合
     */
    Map<String, Object> getAllNoReplyComment(long page, long limit, String userId);
}
