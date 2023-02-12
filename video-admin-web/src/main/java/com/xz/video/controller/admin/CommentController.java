package com.xz.video.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.video.entity.CommentEntity;
import com.xz.video.queryParams.CommentQueryParams;
import com.xz.video.service.CommentService;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后端页面评论模块
 * </p>
 *
 * @author xiaozhao
 * @since 2022-12-20
 */
@RestController
@RequestMapping("/video/comment")
public class CommentController {

      @Autowired
      CommentService commentService;

      /**
       * 根据条件得到所有的评论
       * @param page 当前页
       * @param limit 每页大小
       * @param commentQueryParams 查询条件
       * @return 评论集合
       */
      @RequestMapping("getAllComment/{page}/{limit}")
      public Result getAllComment(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) CommentQueryParams commentQueryParams) {
            Result<IPage<CommentEntity>> result = new Result<>();
            IPage<CommentEntity> pageInfo = commentService.getAllComment(page, limit, commentQueryParams);
            return result.ok(pageInfo);
      }

      /**
       * 删除评论
       * @param commentId 评论id
       */
      @GetMapping("removeAll/{commentId}")
      public Result removeAll(@PathVariable String commentId) {
            Result result = new Result();
            commentService.removeAll(commentId);
            return result.ok();
      }

}
