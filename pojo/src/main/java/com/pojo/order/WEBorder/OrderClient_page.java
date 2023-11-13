package com.pojo.order.WEBorder;

import com.pojo.order.Order;
import com.pojo.order.OrderDetail;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class OrderClient_page extends Order {
    private List<OrderDetail> orderDetailList;
}
