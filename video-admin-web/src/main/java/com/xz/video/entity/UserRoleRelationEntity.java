package com.xz.video.entity;

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
@TableName("t_user_role_relation")
public class UserRoleRelationEntity implements Serializable {


    @TableId(value = "id")
	private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String rolename;
}