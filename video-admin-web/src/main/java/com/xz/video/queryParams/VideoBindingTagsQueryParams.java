package com.xz.video.queryParams;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class VideoBindingTagsQueryParams {

    @ApiModelProperty(value = "视频id")
    private String id;

    @ApiModelProperty(value = "分类ID")
    @NotBlank(message = "二级分类不能为空")
    private String categoryId;

    @ApiModelProperty(value = "分类父级ID")
    @NotBlank(message = "一级分类不能为空")
    private String categoryParentId;

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(value = "销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "视频标题")
    @NotBlank(message = "视频标题不能为空")
    private String title;

    @ApiModelProperty(value = "视频描述")
    @NotBlank(message = "视频描述不能为空")
    private String description;

    @ApiModelProperty(value = "封面图片路径")
    @NotBlank(message = "封面图片路径不能为空")
    private String cover;

    @ApiModelProperty(value = "播放地址")
    @NotBlank(message = "播放地址不能为空")
    private String playerUrl;

    @ApiModelProperty(value = "视频时长")
    @NotBlank(message = "视频时长不能为空")
    private String duration;


    @ApiModelProperty(value = "标签")
    @Size(min = 1)
    private String[] selectTags;
}
