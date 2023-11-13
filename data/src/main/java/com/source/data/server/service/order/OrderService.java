package com.source.data.server.service.order;

import com.pojo.Page.Pages;
import com.pojo.Query.OrderClientQuery;
import com.pojo.Query.OrderServerQuery;
import com.pojo.order.WEBorder.*;

import java.time.LocalDateTime;

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
    Pages<OrderClient_page> selectOrders(OrderClientQuery query);

    /**
     * 查询指定id的订单信息
     * @param id
     * @return
     */
    OrderClient_page selectOrderID(Long id);

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

    /**
     * 客户端分页查询
     * @param page
     * @return
     */
    Pages<OrderServer_page> selectPage(OrderServerQuery page);

    /**
     * 查询订单数量统计
     * @return
     */
    Order_statistics selectStatistics();

    /**
     * 查询指定id的详情
     * @param id
     * @return
     */
    Order_Declared selectDeclaredOrder(Long id);

    /**
     * 接受订单
     *
     * @param id
     */
    void updateConfirmStatus(Long id);

    /**
     * 拒接订单
     * @param updateRejection
     */
    void updateRejectionStatus(Order_updateRejection updateRejection);

    /**
     * 取消订单
     * @param orderUpdateCancel
     */
    void updateCancelStatus(Order_updateCancel orderUpdateCancel);

    /**
     * 完成订单
     * @param id
     */
    void updateCompleteStatus(Long id);

    /**
     * 派送订单
     * @param id
     */
    void updateDelivery(Long id);

    /**
     * 获取指定时间的订单完成率
     * @param bight
     * @param end
     * @return
     */
    Double getDeclaredOrderCompletionRate(LocalDateTime bight, LocalDateTime end);

    /**
     * 获取当天有效订单数
     * @param bight
     * @param end
     * @return
     */
    Integer getDeclaredValidOrderCount(LocalDateTime bight, LocalDateTime end);

    /**
     * 计算当日营业额
     * @param bight
     * @param end
     * @return
     */
    Double getDeclaredTurnover(LocalDateTime bight, LocalDateTime end);

    /**
     * 计算当日平均单价
     * @return
     */
    Double getDeclaredUnitPrice(LocalDateTime bight, LocalDateTime end);

    /**
     * 查询指定状态下的订单总数
     * @return
     */
    Integer selectStatus(Integer status);

}
