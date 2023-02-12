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
@TableName("t_third_part_login")
public class ThirdPartLoginEntity implements Serializable {


    @TableId(value = "id")
	private String id;

    @ApiModelProperty(value = "名称")
	private String name;

    @ApiModelProperty(value = "排序")
	private Integer sort;

    @ApiModelProperty(value = "图片")
    private Integer image;

    @ApiModelProperty(value = "资源")
    private String source;

    @ApiModelProperty(value = "状态")
    private Integer status;

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