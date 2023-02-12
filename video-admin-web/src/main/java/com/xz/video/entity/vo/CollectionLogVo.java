package com.xz.video.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CollectionLogVo {

    private String videoId;

    private String title;

    private String cover;

    private String description;

    private Long viewCount;

    private Date time;
}