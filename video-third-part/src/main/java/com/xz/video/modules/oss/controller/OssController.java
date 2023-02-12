package com.xz.video.modules.oss.controller;


import com.xz.video.modules.oss.service.OssService;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * oss对象存储模块
 */
@RestController
@RequestMapping("thirdPart/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传文件
    @PostMapping("uploadOssFile")
    public Result uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        Result<String> result = new Result<>();
        String url = ossService.uploadFileAvatar(file);
        return result.ok(url);
    }

    @PostMapping("uploadVideoFile")
    public Result uploadVideoFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        Result<Map<String,String>> result = new Result<>();
        Map<String,String> map = ossService.uploadVideoFile(file);
        return result.ok(map);
    }

    //删除文件
    @RequestMapping("deleteFile")
    public Result deleteFile(String fileName){
        Result<String> result = new Result<>();
        ossService.deleteFile(fileName);
        return result.ok();
    }


}
