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
@TableName("t_role_function_relation")
public class RoleFunctionRelationEntity implements Serializable {


    @TableId(value = "id")
	private String id;

    @ApiModelProperty(value = "角色id")
	private String roleId;

    @ApiModelProperty(value = "功能id")
	private String functionId;
}