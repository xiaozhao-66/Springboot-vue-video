package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName t_tag
 */
@TableName(value ="t_tag")
@Data
public class TagEntity implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(value = "id")
    private String id;


    @ApiModelProperty(value = "名称")
    private String label;


    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}