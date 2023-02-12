package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Comment对象", description="用户评论")
@TableName(value = "t_comment")
public class CommentEntity implements Serializable {

    @ApiModelProperty("评论id")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty("视频id")
    private String videoId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("根评论id")
    private String rootCommentId;

    @ApiModelProperty("回复评论id")
    private String replyCommentId;

    @ApiModelProperty("回复评论id")
    private String replyCommentUsername;

    @ApiModelProperty("level")
    private Integer level;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否查看")
    private Integer isLook;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private IPage<CommentEntity> children;

    @ApiModelProperty("主评论")
    @TableField(exist = false)
    private String mContent;
}
