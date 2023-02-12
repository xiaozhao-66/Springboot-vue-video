package com.xz.video.websocket;

import com.alibaba.fastjson.JSON;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.vo.MessageVo;
import com.xz.video.service.MessageService;
import com.xz.video.service.impl.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.jar.JarOutputStream;

/**
 * @Author:gzh
 * @Date: 2022/4/20 20:27
 * 注意在websocket通信中只能传string
 */
@ServerEndpoint("/webSocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    //    存储当前对象
    public static final ConcurrentHashMap<String, WebSocketServer> sessionMap = new ConcurrentHashMap<>();

    private Session session;

    private String userId;

    /***
     * 1.把登录用户存到sessionMap中
     * 2.发送给所有人当前登录人员信息
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        System.out.println("当前用户id=="+userId);
        log.info("现在来连接的客户id：" + session.getId() + "用户名：" + userId);
        this.session = session;
        this.userId = userId;
        sessionMap.put(userId, this);
    }

    //关闭连接
    /**
     * 1.把登出的用户从sessionMap中剃除
     * 2.发送给所有人当前登录人员信息
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        sessionMap.remove(userId);
    }

    /**
     * 接收处理客户端发来的数据
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    //    服务端发送消息给指定客户端
    public void sendMessageTo(String message, String ToUserId) throws IOException {
        for (WebSocketServer item : sessionMap.values()) {
            if (item.userId.equals(ToUserId)) {
                //异步发送 最好控制发送间隔
                item.session.getAsyncRemote().sendText(message);
                //同步发送==>高并发使用，可不用控制发送间隔
                //item.session.getBasicRemote().sendText(message);
                return;
            } else {
//                webSocketService.saveMassage(ToUserName,message);
                return;
            }
        }
    }


    //   服务端发送消息给所有客户端
    private void sendAllMessage(String message) {
        for (WebSocketServer item : sessionMap.values()) {
            item.session.getAsyncRemote().sendText(message);
        }

    }
}
