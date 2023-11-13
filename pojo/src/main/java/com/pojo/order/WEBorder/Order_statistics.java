package com.pojo.order.WEBorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order_statistics {
    private Integer confirmed;  // 待派送数量
    private Integer deliveryInProgress; // 派送中数量
    private Integer toBeConfirmed; // 待接单数量
}
