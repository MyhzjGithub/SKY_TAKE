package com.source.data.server.server_controller.workspace;

import com.pojo.RESULT.Result;
import com.pojo.workspace.workspace_businessData;
import com.pojo.workspace.workspace_overviewDishs;
import com.pojo.workspace.workspace_overviewOrders;
import com.pojo.workspace.workspace_overviewSetmeals;
import com.source.data.server.service.workspace.WorkspaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/admin/workspace")
public class WorkspaceController {
    @Autowired
    private WorkspaceService service;

    @GetMapping("/businessData")
    public Result businessData(){
        log.info("查询今日运营数据");
        workspace_businessData data = service.selectBusinessData();
        return Result.success(data);
    }
    @GetMapping("/overviewSetmeals")
    public Result overviewSetmeals(){
        log.info("查询套餐总览");
        workspace_overviewSetmeals data = service.selectOverviewSetmeals();
        return Result.success(data);
    }

    @GetMapping("/overviewDishes")
    public Result overviewDishes(){
        log.info("查询菜品总览");
        workspace_overviewDishs data = service.selectOverviewDishs();
        return Result.success(data);
    }

    @GetMapping("/overviewOrders")
    public Result overviewOrders(){
        log.info("查询订单管理数据");
        workspace_overviewOrders data = service.selectOverviewOrders();
        return Result.success(data);
    }
}
