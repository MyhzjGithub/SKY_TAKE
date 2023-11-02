package com.pojo.order;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单管理类
 */
@Data
public class order  implements Serializable {
    private Long id;    // 订单id
    private String number;  // 订单号
    private Integer status; // 订单状态
    private Long userId;    // 下单用户id
    private Long addressBookId; // 地址id
    private LocalDateTime orderTime;    // 下单时间
    private LocalDateTime checkoutTime; // 结账时间
    private Integer payMethod;  // 支付方式 1 微信 2 支付宝
    private Integer payStatus;  // 支付状态 0 未支付 1 已支付 2 退款
    private BigDecimal amount;  // 实际付款金额
    private String remark;  // 订单备注
    private String phone;   // 用户手机号
    private String address; // 详细地址
    private String userName;    // 用户名称
    private String consignee;   // 收货人
    private String cancelReason;    // 订单取消原因
    private String rejectionReason; // 订单拒绝原因
    private LocalDateTime cancelTime;   // 订单取消时间
    private LocalDateTime estimatedDeliveryTime;    // 预计送达时间
    private Integer deliveryStatus; // 配送状态 1 立即送出 0 选择具体时间
    private LocalDateTime deliveryTime; // 送达时间
    private Integer packAmount; // 打包费
    private Integer tablewareNumber;    // 餐具数量
    private Integer tablewareStatus;    // 餐具数量状态 1 按餐量提供 0 选择具体餐具
}
