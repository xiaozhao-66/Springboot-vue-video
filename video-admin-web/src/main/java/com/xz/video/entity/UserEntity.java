package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author testjava
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="user对象", description="")
@TableName(value = "t_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "增加视频的数量")
    private Integer addVideoCount;

    @ApiModelProperty(value = "收藏视频的数量")
    private Integer collectionVideoCount;

    @ApiModelProperty(value = "关注的数量")
    private Integer followCount;

    @ApiModelProperty(value = "粉丝的数量")
    private Integer fanCount;

    @ApiModelProperty(value = "没有回复评论的数量")
    private Integer noReplyCommentCount;

    @ApiModelProperty(value = "没有回复私信的消息数量")
    private Integer noReplyLetterCount;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
