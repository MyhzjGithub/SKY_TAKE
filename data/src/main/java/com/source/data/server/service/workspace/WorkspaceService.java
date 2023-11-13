package com.source.data.server.service.workspace;

import com.pojo.workspace.workspace_businessData;
import com.pojo.workspace.workspace_overviewDishs;
import com.pojo.workspace.workspace_overviewOrders;
import com.pojo.workspace.workspace_overviewSetmeals;

public interface WorkspaceService {
    /**
     * 查询今日运营数据
     * @return
     */
    workspace_businessData selectBusinessData();

    /**
     * 查询套餐总览
     * @return
     */
    workspace_overviewSetmeals selectOverviewSetmeals();

    /**
     * 查询菜品总览
     * @return
     */
    workspace_overviewDishs selectOverviewDishs();

    /**
     * 查询订单管理数据
     * @return
     */
    workspace_overviewOrders selectOverviewOrders();

}
