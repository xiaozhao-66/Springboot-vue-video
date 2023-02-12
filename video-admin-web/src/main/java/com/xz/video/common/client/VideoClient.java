package com.xz.video.common.client;

import io.renren.common.entity.dm.DmMessage;
import io.renren.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *     视频服务类
 * </p>
 * @author xiaozhao
 */
@FeignClient(value = "video-third-part")
@Component
public interface VideoClient {


    /**
     * 根据视频id删除阿里云视频
     */
    @DeleteMapping("/thirdPart/videoPlayer/removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable String id);

    /**
     * 得到视频凭证
     */
    @GetMapping("/thirdPart/videoPlayer/getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id);

    /**
     * 得到视频的地址
     */
    @GetMapping("/thirdPart/videoPlayer/getPlayUrl/{id}")
    public Result getPlayUrl(@PathVariable String id);

    /**
     * 发送邮件
     */
    @PostMapping("/thirdPart/dm/sendDm")
    public Result sendDm(@RequestBody DmMessage message);

}
