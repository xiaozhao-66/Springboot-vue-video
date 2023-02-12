package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 私信功能
 */
@Data
@TableName("t_message")
public class MessageEntity implements Serializable {

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "发送方id")
    private String sendUserId;

    @ApiModelProperty(value = "接受方id")
    private String acceptUserId;

    @ApiModelProperty(value = "数据")
    private String data;

    @ApiModelProperty(value = "时间")
    private String time;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "用户集合")
    @TableField(exist = false)
    private List<String> userList;
}
