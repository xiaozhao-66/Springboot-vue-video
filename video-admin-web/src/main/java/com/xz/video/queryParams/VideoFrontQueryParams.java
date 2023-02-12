package com.xz.video.queryParams;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoFrontQueryParams {

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "查询信息")
    private String searchInfo;
}
