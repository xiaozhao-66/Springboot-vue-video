package com.xz.video.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.entity.OrderEntity;
import com.xz.video.entity.vo.OrderVo;
import com.xz.video.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *     支付宝工具类
 * </p>
 *
 * @author xiaozhao
 */
@Slf4j
@Component
public class AliPayUtils {

    @Autowired
    //添加懒加载防止循环依
    //@Lazy
    OrderService orderService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String pay(OrderVo orderVo) throws AlipayApiException {
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConstant.GATEWAY_URL,
                AliPayConstant.APP_ID, AliPayConstant.MERCHANT_PRIVATE_KEY, "json",
                AliPayConstant.CHARSET, AliPayConstant.ALIPAY_PUBLIC_KEY, AliPayConstant.SIGN_TYPE);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();

        OrderEntity order = orderService.getOne(new QueryWrapper<OrderEntity>().eq("order_no", orderVo.getOut_trade_no()));
        alipayRequest.setReturnUrl("http://43.248.97.192:9956/dashboard/videoInfo/"+order.getVideoId());
        alipayRequest.setNotifyUrl(AliPayConstant.NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String outTradeNo = orderVo.getOut_trade_no();
        //付款金额，必填
        String totalAmount = orderVo.getTotal_amount();
        //订单名称，必填
        String subject = orderVo.getSubject();
        String timeout = "1c";
        //商品描述，可空
        String body= orderVo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        return result;
    }

    public String handleAlipayed(PayAsyncVo payAsyncVo, HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 只要收到支付宝的异步通知，返回 success 支付宝便不再通知
        // 获取支付宝POST过来反馈信息
        //TODO 需要验签
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConstant.ALIPAY_PUBLIC_KEY, AliPayConstant.CHARSET, AliPayConstant.SIGN_TYPE); //调用SDK验证签名

        if (signVerified) {
            String tradeStatus = payAsyncVo.getTrade_status();
            if("TRADE_SUCCESS".equals(tradeStatus)|| "TRADE_FINISHED".equals(tradeStatus)){
                //如果返回成功就可以对状态进行修改
                //得到订单号
                String outTradeNo = payAsyncVo.getOut_trade_no();
                OrderEntity order = orderService.getOne(new QueryWrapper<OrderEntity>().eq("order_no", outTradeNo));
                order.setStatus(1);
                orderService.updateById(order);
                //往邮件队列中添加一条消息，
                rabbitTemplate.convertAndSend("dm-event-exchange", "dm.send", order);
                return "success";
            }
            return "error";

        } else {
            return "error";
        }
    }
}
