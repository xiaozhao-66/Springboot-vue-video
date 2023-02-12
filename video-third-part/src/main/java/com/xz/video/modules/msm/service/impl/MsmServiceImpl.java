package com.xz.video.modules.msm.service.impl;

import cn.hutool.core.lang.hash.Hash;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.expression.ExpressionException;
import com.alibaba.fastjson.JSONObject;
import com.xz.video.modules.msm.service.MsmService;
import io.renren.common.redis.RedisUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("msmService")
public class MsmServiceImpl implements MsmService {

    @Override
    public String sendMsm(String phone) throws Exception {
        String code = RandomUtil.randomNumbers(4);
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setEndpoint("dysmsapi.aliyuncs.com")
                // 必填，您的 AccessKey ID
                .setAccessKeyId("LTAI4G3DxLCmrmF2pD8PHc84")
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret("WraTKDlqJFL4vaI0UCWsnZZp7R0Wka");
        com.aliyun.dysmsapi20170525.Client client = new com.aliyun.dysmsapi20170525.Client(config);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("我的谷粒学院在线网站")
                .setTemplateCode("SMS_198692078")
                .setTemplateParam(JSONObject.toJSONString(param));
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            client.sendSmsWithOptions(sendSmsRequest, runtime);
            return code;
        } catch (Exception e) {
            throw new ExpressionException("发送短信失败");
        }
    }
}
