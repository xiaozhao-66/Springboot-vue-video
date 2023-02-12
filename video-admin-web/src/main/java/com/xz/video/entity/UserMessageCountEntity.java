package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "t_user_message_count")
public class UserMessageCountEntity implements Serializable {

    @TableId(value = "id")
    private String id;

    @ApiModelProperty("当前用户")
    private String currentUserId;

    @ApiModelProperty("谁给我发的消息用户")
    private String fromUserId;

    @ApiModelProperty("消息数量")
    private Integer count;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
