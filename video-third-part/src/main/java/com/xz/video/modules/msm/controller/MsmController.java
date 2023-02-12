package com.xz.video.modules.msm.controller;


import com.xz.video.modules.msm.redis.RedisUtils;
import com.xz.video.modules.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *发送信息的功能
 */
@RestController
@RequestMapping("/thirdPart/msm")
public class MsmController {


    @Autowired
    MsmService msmService;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("sendMsm/{phone}")
    public void sendMsm(@PathVariable String phone) throws Exception {
        String code = msmService.sendMsm(phone);
        redisUtils.set("code", code, 60 * 5L);
    }

}
