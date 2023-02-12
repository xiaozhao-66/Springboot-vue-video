package com.xz.video.common.task;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


/**
  *定时清除数据库中浏览记录缓存和聊天记录缓存
 */
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class CancelTask {




}
