package com.pojo.order.WEBorder;

import com.pojo.order.Order;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderServer_page extends Order {

    private String orderDishes; // 订单包含的菜品信息

}
