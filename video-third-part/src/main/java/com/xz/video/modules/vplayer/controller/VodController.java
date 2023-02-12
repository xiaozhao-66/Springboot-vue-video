package com.xz.video.modules.vplayer.controller;


import com.xz.video.modules.vplayer.service.VodService;
import io.renren.common.exception.RenException;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 视频模块
 */
@RestController
@RequestMapping("/thirdPart/videoPlayer")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public Result uploadAlyiVideo(MultipartFile file) {
        Result<String> result = new Result();
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return result.ok(videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable String id) {
        Result<String> result = new Result();
        vodService.removeAlyVideo(id);
        return result.ok();
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return new Result().ok();
    }


    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id) {
        Result<String> result = new Result<>();
        String playAuth = vodService.getPlayAuth(id);
        return result.ok(playAuth);
    }

    //获取视频的地址
    @GetMapping("getPlayUrl/{id}")
    public Result getPlayUrl(@PathVariable String id) {
        Result<String> result = new Result<>();
        String playUrl = vodService.getPlayUrl(id);
        return result.ok(playUrl);
    }

}
