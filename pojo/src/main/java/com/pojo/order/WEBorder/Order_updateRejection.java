package com.pojo.order.WEBorder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order_updateRejection {
    private Long id;
    private String rejectionReason; // 订单拒绝原因
}
