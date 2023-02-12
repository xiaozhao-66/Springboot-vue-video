package com.xz.video.alipay;

import com.alipay.api.AlipayApiException;
import com.xz.video.entity.vo.OrderVo;
import com.xz.video.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 *     支付宝模块
 * </p>
 *
 * @author xiaozhao
 */
@RestController
@RequestMapping("video/zhifubao")
public class ZhiFuBaoPayController {

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AliPayUtils aliPayUtils;

    /**
     * 支付接口
     * @param orderVo 订单类
     * @return 支付响应页面
     * @throws AlipayApiException
     */
    @RequestMapping("pay")
    public String pay(OrderVo orderVo) throws AlipayApiException {
       return aliPayUtils.pay(orderVo);
    }


    /**
     * 异步通知类
     * @param payAsyncVo 异步支付类
     * @param request 请求
     * @return 支付状态
     * @throws AlipayApiException
     * @throws UnsupportedEncodingException
     */
    @PostMapping("payed/notify")
    public String handleAlipayed(PayAsyncVo payAsyncVo, HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        return aliPayUtils.handleAlipayed(payAsyncVo, request);
    }
}
