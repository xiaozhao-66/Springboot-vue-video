package com.xz.video.entity.vo;

import com.xz.video.entity.TagEntity;
import com.xz.video.entity.VideoEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VideoInfoWithTagVo extends VideoEntity {

    private String[] selectTags;

    private List<TagEntity> tags;

}
