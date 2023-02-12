package com.xz.video.modules.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);

    void deleteFile(String fileName);

    Map<String, String> uploadVideoFile(MultipartFile file);
}
