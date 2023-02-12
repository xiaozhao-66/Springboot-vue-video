package com.xz.video.videoadminweb;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.video.common.redis.RedisUtils;
import com.xz.video.entity.VideoEntity;
import com.xz.video.entity.vo.VideoWithViewLogsVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Date;
import java.util.Set;

@SpringBootTest
class VideoAdminWebApplicationTests {

    @Test
    void contextLoads() {

    }


}
