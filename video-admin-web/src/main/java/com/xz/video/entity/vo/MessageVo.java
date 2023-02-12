package com.xz.video.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class MessageVo {

    private String fromUserId;

    private String toUserId;

    private String fromUsername;

    private String toUsername;

    private String data;

    private String time;

    private Date dateTime;

    private List<String> userList;
}
