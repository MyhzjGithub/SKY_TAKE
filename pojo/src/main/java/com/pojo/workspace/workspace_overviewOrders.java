package com.pojo.workspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class workspace_overviewOrders {
    private Integer allOrders; // 全部订单
    private Integer cancelledOrders; // 已取消数量
    private Integer completedOrders; // 已完成数量
    private Integer deliveredOrders; // 待派送数量
    private Integer waitingOrders; // 待接单数量

}
