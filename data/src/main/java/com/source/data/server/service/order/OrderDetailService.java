package com.source.data.server.service.order;

import com.pojo.order.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    /**
     * 添加订单明细表
     * @param orderDetailList
     */
    void insert(List<OrderDetail> orderDetailList);

    List<OrderDetail> getOrderId(Long orderId);
}
