package com.pojo.order.WEBorder;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class Order_submit {
    private Long addressBookId; //  地址簿id
    private Double amount;  //总金额
    private Integer deliveryStatus; // 配送状态：  1立即送出  0选择具体时间
    private String estimatedDeliveryTime; // 预计送达时间
    private Integer packAmount; // 打包费
    private Integer payMethod; // 付款方式
    private String remark; // 备注
    private Integer tablewareNumber; // 餐具数量
    private Integer tablewareStatus; // 餐具数量状态  1按餐量提供  0选择具体数量
}
