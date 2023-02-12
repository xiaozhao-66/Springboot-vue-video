package com.xz.video.service.impl;

import cn.hutool.core.util.PageUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.video.dao.CommentDao;
import com.xz.video.entity.CommentEntity;
import com.xz.video.entity.FollowEntity;
import com.xz.video.entity.UserEntity;
import com.xz.video.queryParams.CommentQueryParams;
import com.xz.video.service.CommentService;
import com.xz.video.service.UserMessageCountService;
import com.xz.video.service.UserService;
import com.xz.video.utils.PageUtils;
import com.xz.video.websocket.WebSocketServer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;;
import java.util.HashMap;
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
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Autowired
    UserService userService;

    @Autowired
    WebSocketServer webSocketServer;

    @Autowired
    UserMessageCountService userMessageCountService;

    /**
     * 根据视频ID得到所有的评论
     * @param videoId 视频id
     * @param page 一级评论分页
     * @param limit 一级评论分页大小
     * @param page2 二级评论
     * @param limit2 二级评论分页大小
     * @return 评论集合
     */
    @Override
    public IPage<CommentEntity> getAllCommentByVideo(String videoId, long page, long limit, long page2, long limit2) {
        Page<CommentEntity> pageLevel1 = new Page<>(page, limit);
        QueryWrapper<CommentEntity> rootQueryWrapper = new QueryWrapper<CommentEntity>().and(e -> e.eq("root_comment_id", "0").eq("video_id", videoId)).orderByDesc("gmt_create");
        Page<CommentEntity> selectPage = this.baseMapper.selectPage(pageLevel1,rootQueryWrapper);

        for (CommentEntity rootComment : selectPage.getRecords()) {

            QueryWrapper<CommentEntity> twoQueryWrapper = new QueryWrapper<CommentEntity>().eq("root_comment_id",rootComment.getId()).orderByDesc("gmt_create");
            Page<CommentEntity> pageLevel2 = new Page<>(page2,limit2);
            IPage<CommentEntity> twoCommentList = this.baseMapper.selectPage(pageLevel2,twoQueryWrapper);
            rootComment.setChildren(twoCommentList);
        }
        return selectPage;
    }

    /**
     * 根据条件得到所有的评论
     * @param page 当前页
     * @param limit 每页大小
     * @param commentQueryParams 查询条件
     * @return 评论集合
     */
    @Override
    public IPage<CommentEntity> getAllComment(long page, long limit, CommentQueryParams commentQueryParams) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        //创建page对象
        Page<CommentEntity> commentPage = new Page<>(page, limit);
        String content = commentQueryParams.getTitle();
        String begin = commentQueryParams.getBegin();
        String end = commentQueryParams.getEnd();
        if (!StringUtils.isEmpty(content)) {
            queryWrapper.like("username", content).or().like("content", content);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        return this.baseMapper.selectPage(commentPage, queryWrapper);
    }

    /**
     * 删除评论
     * @param commentId 评论id
     */
    @Override
    public void removeAll(String commentId) {
        this.baseMapper.deleteById(commentId);
    }

    /**
     * 增加评论
     * @param commentEntity 评论实体
     */
    @Override
    public void addComment(CommentEntity commentEntity) throws IOException {
        this.baseMapper.insert(commentEntity);
        //回复用户的评论数量加1
        if (!"0".equals(commentEntity.getReplyCommentId())) {
            //回复的评论
            CommentEntity comment = this.baseMapper.selectById(commentEntity.getReplyCommentId());
            String userId = comment.getUserId();
            UserEntity user = userService.getById(userId);
            user.setNoReplyCommentCount(user.getNoReplyCommentCount() + 1);
            userService.updateById(user);
            int[] res = userMessageCountService.getUserNoReplyCount(userId);
            webSocketServer.sendMessageTo(JSON.toJSONString(res),userId);
        }
    }

    /**
     * 得到用户所有未回复的评论
     * @param userId 用户id
     * @return 评论集合
     */
    @Override
    public Map<String,Object> getAllNoReplyComment(long page, long limit, String userId) {

        Map<String,Object> map = new HashMap<>();

        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByDesc("gmt_create");
        //得到当前用户所有的评论
        List<CommentEntity> commentEntities = this.baseMapper.selectList(queryWrapper);
        //返回所有给我评论的评论信息
        List<CommentEntity> allComment = this.getAllComment(commentEntities);

        //修改当前用户未查看的评论为空
        UserEntity user = userService.getById(userId);
        user.setNoReplyCommentCount(0);
        userService.updateById(user);

        if(!allComment.isEmpty()){
            Page pages = PageUtils.getPages((int)page, (int)limit, allComment);
            long count = pages.getTotal();
            map.put("count",count);
            map.put("list",pages.getRecords());
        }else {
            map.put("count",0);
            map.put("list",null);
        }
        return map;
    }


    private List<CommentEntity> getAllComment(List<CommentEntity> commentList){
        List<CommentEntity> res = new ArrayList<>();

        for (CommentEntity model : commentList) {
            //回复的评论肯定都是二级评论
            QueryWrapper<CommentEntity> query = new QueryWrapper<>();
            query.eq("reply_comment_id", model.getId());
            query.orderByDesc("gmt_create");
            List<CommentEntity> replyComments = this.baseMapper.selectList(query);
            if (replyComments != null && !replyComments.isEmpty()) {
                for (CommentEntity e:replyComments) {
                    e.setMContent(model.getContent());
                    e.setIsLook(1);
                    this.baseMapper.updateById(e);
                    res.add(e);
                }
            }
        }
        return res;

    }
}
