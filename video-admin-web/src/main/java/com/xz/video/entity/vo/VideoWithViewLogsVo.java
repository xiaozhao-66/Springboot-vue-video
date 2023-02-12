package com.xz.video.entity.vo;

import com.xz.video.entity.VideoEntity;
import lombok.Data;


import java.util.Date;

@Data
public class VideoWithViewLogsVo extends VideoEntity {
    private Date updateTime;
}
