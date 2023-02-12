package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName t_videoinfo_tag_relation
 */
@TableName(value ="t_videoinfo_tag_relation")
@Data
public class VideoinfoTagRelationEntity implements Serializable {
    /**
     * 
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id")
    private String id;


    @ApiModelProperty(value = "视频id")
    private String videoId;


    @ApiModelProperty(value = "标签id集合")
    private String tagsId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}