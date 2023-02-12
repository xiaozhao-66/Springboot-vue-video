package com.xz.video.modules.msm.controller;


import com.xz.video.modules.msm.service.DmService;
import io.renren.common.entity.dm.DmMessage;
import io.renren.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *发送邮件的功能
 */
@RestController
@RequestMapping("/thirdPart/dm")
public class DmController {

    @Autowired
    DmService dmService;

    @PostMapping("sendDm")
    public Result sendDm(@RequestBody DmMessage message){
        try {
            dmService.sendDm(message.getToAddress(),message.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result().ok();
    }

}
