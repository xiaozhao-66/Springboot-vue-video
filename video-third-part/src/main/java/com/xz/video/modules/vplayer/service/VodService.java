package com.xz.video.modules.vplayer.service;

import io.renren.common.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);

    void removeAlyVideo(String id);

    String getPlayAuth(String id);

    String getPlayUrl(String id);
}
