package com.xz.video.entity.vo;

import com.xz.video.entity.UserEntity;
import lombok.Data;

@Data
public class UserVo extends UserEntity {

    private String retypePassword;
    private String code;
}
