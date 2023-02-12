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
@TableName("t_role")
public class RoleEntity implements Serializable {


    @TableId(value = "id")
	private String id;

    @ApiModelProperty(value = "角色名称")
	private String name;

    @ApiModelProperty(value = "角色昵称")
	private String remark;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;
    /**
     * 
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;
}