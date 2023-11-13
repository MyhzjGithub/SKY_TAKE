package com.source.data.server.service.workspace.impl;

import com.pojo.workspace.workspace_businessData;
import com.pojo.workspace.workspace_overviewDishs;
import com.pojo.workspace.workspace_overviewOrders;
import com.pojo.workspace.workspace_overviewSetmeals;
import com.source.data.server.service.dish.DishService;
import com.source.data.server.service.order.OrderService;
import com.source.data.server.service.setmeal.SetmealService;
import com.source.data.server.service.user.userService;
import com.source.data.server.service.workspace.WorkspaceService;
import com.utils.OrderUtils.orderStatus;
import com.utils.StatusUtils.DefaultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.Set;

@Service
public class WorkspaceClass implements WorkspaceService {
    @Autowired
    private userService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private DishService dishService;

    @Override
    public workspace_businessData selectBusinessData() {
        LocalDate now = LocalDate.now();
        LocalDateTime bight = LocalDateTime.of(now, LocalTime.MIN); // 当天的起始时间
        LocalDateTime end = LocalDateTime.of(now, LocalTime.MAX);   // 结束最大时间
        // 获取新增用户量
        Integer newUSer = userService.getDeclareduser(bight,end);
        // 计算订单完成率
        Double orderCompletionRate = orderService.getDeclaredOrderCompletionRate(bight,end);
        // 当日营业额
        Double turnover = orderService.getDeclaredTurnover(bight,end);
        // 平均单价
        Double unitPrice = orderService.getDeclaredUnitPrice(bight,end);
        // 有效订单数
        Integer validOrderCount = orderService.getDeclaredValidOrderCount(bight, end);
        return new workspace_businessData(newUSer, orderCompletionRate, turnover, unitPrice, validOrderCount);
    }

    @Override
    public workspace_overviewSetmeals selectOverviewSetmeals() {
        // 停售套餐
        Integer discontinued = setmealService.selectStatus(DefaultStatus.ZERO);
        // 启售套餐
        Integer sold = setmealService.selectStatus(DefaultStatus.ONE);
        return new workspace_overviewSetmeals(discontinued, sold);
    }

    @Override
    public workspace_overviewDishs selectOverviewDishs() {
        // 停售菜品
        Integer discontinued = dishService.selectStatus(DefaultStatus.ZERO);
        // 启售菜品
        Integer sold = dishService.selectStatus(DefaultStatus.ONE);
        return new workspace_overviewDishs(discontinued,sold);
    }

    @Override
    public workspace_overviewOrders selectOverviewOrders() {
        // 全部订单
        Integer allOrders = orderService.selectStatus(null);
        // 已取消数量
        Integer cancelledOrders = orderService.selectStatus(orderStatus.SIX);
        // 已完成数量
        Integer completedOrders = orderService.selectStatus(orderStatus.FIVE);
        // 待派送数量
        Integer deliveredOrders = orderService.selectStatus(orderStatus.THREE);
        // 待接单数量
        Integer waitingOrders = orderService.selectStatus(orderStatus.TWO);
        return new workspace_overviewOrders(allOrders, cancelledOrders, completedOrders, deliveredOrders, waitingOrders);
    }
}
