package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_videoInfo")
@ApiModel(value="Vid对象", description="课程")
public class VideoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "分类ID")
    private String categoryId;

    @ApiModelProperty(value = "分类父级ID")
    private String categoryParentId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "视频标题")
    private String title;

    @ApiModelProperty(value = "视频描述")
    private String description;

    @ApiModelProperty(value = "销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "封面图片路径")
    private String cover;

    @ApiModelProperty(value = "播放地址")
    private String playerUrl;

    @ApiModelProperty(value = "数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "收藏数量")
    private Long collectionCount;

    @ApiModelProperty(value = "点赞数量")
    private Long supportCount;

    @ApiModelProperty(value = "浏览数量")
    private Long unsupportCount;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "课程状态 0未发布  1已发布")
    private int status;

    @ApiModelProperty(value = "视频时长")
    private String duration;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}
