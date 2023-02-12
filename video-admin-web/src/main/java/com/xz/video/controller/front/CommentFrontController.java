package com.xz.video.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.CommentEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.service.CommentService;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * <p>
 * 评论前台模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("video/front/comment")
@Slf4j
public class CommentFrontController {

    @Autowired
    CommentService commentService;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 根据视频ID得到所有的评论
     * @param videoId 视频id
     * @param page 一级评论分页
     * @param limit 一级评论分页大小
     * @param page2 二级评论
     * @param limit2 二级评论分页大小
     * @return 评论集合
     */
    @RequestMapping("getAllCommentByVideo/{page}/{limit}/{page2}/{limit2}")
    public Result getAllCommentByVideo(@PathVariable long page, @PathVariable long limit,@PathVariable long page2, @PathVariable long limit2,String videoId ) {
        Result<IPage<CommentEntity>> result = new Result<>();
        IPage<CommentEntity> pageInfo = commentService.getAllCommentByVideo(videoId,page,limit,page2,limit2);
        return result.ok(pageInfo);
    }

    /**
     * 获取当前评论
     * @param commentId 评论id
     */
    @RequestMapping("getOneComment/{commentId}")
    public Result getOneComment(@PathVariable String commentId) {
        Result<CommentEntity> result = new Result<>();
        CommentEntity comment = commentService.getOne(new QueryWrapper<CommentEntity>().eq("id", commentId));
        return result.ok(comment);
    }

    /**
     * 增加评论
     * @param commentEntity 评论实体
     */
    @RequestMapping("addComment")
    public Result addComment(@RequestBody CommentEntity commentEntity)throws IOException {
        Result result = new Result();
        commentService.addComment(commentEntity);
        return result.ok();
    }

    /**
     * 得到用户所有未回复的评论
     * @param userId 用户id
     * @return 评论集合
     */
    @RequestMapping("getAllNoReplyComment/{page}/{limit}")
    public Result getAllNoReplyComment(@PathVariable long page, @PathVariable  long limit,String userId) {
        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object>  commentList = commentService.getAllNoReplyComment(page,limit,userId);
        return result.ok(commentList);
    }
}
