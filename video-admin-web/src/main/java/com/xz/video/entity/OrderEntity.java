package com.xz.video.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-11-18
 */
@Data
@TableName("t_order")
public class OrderEntity implements Serializable {


    @TableId("id")
	private String id;

    @ApiModelProperty(value = "订单号")
	private String orderNo;

    @ApiModelProperty(value = "视频id")
	private String videoId;

    @ApiModelProperty(value = "视频标题")
	private String videoTitle;

    @ApiModelProperty(value = "价格")
	private BigDecimal price;

    @ApiModelProperty(value = "封面")
	private String videoCover;

    @ApiModelProperty(value = "用户id")
	private String memberId;


    @ApiModelProperty(value = "会员昵称")
	private String username;

    @ApiModelProperty(value = "手机")
	private String mobile;

    @ApiModelProperty(value = "支付类型（1：微信 2：支付宝）")
	private Integer payType;

    @ApiModelProperty(value = "订单状态（0：未支付 1：已支付）")
	private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;
}