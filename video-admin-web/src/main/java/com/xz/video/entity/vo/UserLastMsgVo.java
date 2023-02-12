package com.xz.video.entity.vo;

import com.xz.video.entity.UserMessageCountEntity;
import lombok.Data;

import java.util.Date;

//返回私信用户的信息
@Data
public class UserLastMsgVo extends UserMessageCountEntity {

    private String username;

    private String avatar;


}
