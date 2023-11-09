package com.source.data.server.service.order;

import com.pojo.Page.Pages;
import com.pojo.Query.OrderQuery;
import com.pojo.order.WEBorder.Order_message;
import com.pojo.order.WEBorder.Order_page;
import com.pojo.order.WEBorder.Order_submit;

public interface OrderService {
    /**
     * 新增订单信息
     * @param orderSubmit
     * @return
     */
    Order_message insert(Order_submit orderSubmit);

    /**
     * 分页查询当前用户的历史订单
     * @param query
     * @return
     */
    Pages<Order_page> selectOrders(OrderQuery query);

    /**
     * 查询指定id的订单信息
     * @param id
     * @return
     */
    Order_page selectOrderID(Long id);

    /**
     * 取消指定id的订单
     * @param id
     */
    void updateOrderId(Long id);

    /**
     * 催促订单
     * @param id
     */
    void reminder(Long id);

    /**
     * 再来一单
     * @param id
     */
    void openOrder(Long id);
}
