package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-11-18
 */
@Data
@TableName("t_user_third_part_relation")
public class UserThirdPartRelationEntity implements Serializable {

    @ApiModelProperty(value = "ID")
    @TableId(value = "id")
	private String id;

    @ApiModelProperty(value = "用户id")
	private String userId;

    @ApiModelProperty(value = "排序")
	private Integer sort;

    @ApiModelProperty(value = "手机号")
	private String phone;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "第三方登录id")
	private String thirdPartLoginId;

    @ApiModelProperty(value = "第三方登录用户id")
	private String thirdPartUserId;

    @ApiModelProperty(value = "第三方登录用户名称")
	private String thirdPartUsername;

    @ApiModelProperty(value = "第三方登录用户头像")
	private String thirdPartAvatar;

    @ApiModelProperty(value = "第三方登录用户token")
	private String thirdPartToken;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;
    /**
     * 
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;
}