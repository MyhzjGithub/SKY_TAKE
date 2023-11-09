package com.source.data.server.service.order.impl;

import com.pojo.order.OrderDetail;
import com.source.data.server.dao.order.OrderDetailMapper;
import com.source.data.server.service.order.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailClass implements OrderDetailService {
    @Autowired
    private OrderDetailMapper mapper;
    @Override
    public void insert(List<OrderDetail> orderDetailList) {
        mapper.insert(orderDetailList);
    }

    @Override
    public List<OrderDetail> getOrderId(Long orderId) {
        return mapper.getOrderId(orderId);
    }
}
