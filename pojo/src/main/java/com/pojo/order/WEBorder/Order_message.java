package com.pojo.order.WEBorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order_message {
    private Long id;    // 订单id
    private Double orderAmount; // 订单金额
    private String orderNumber; // 订单号
    private LocalDateTime orderTime; // 下单时间
}
