package com.source.data.server.server_controller.order;

import com.pojo.Page.Pages;
import com.pojo.Query.OrderServerQuery;
import com.pojo.RESULT.Result;
import com.pojo.order.WEBorder.*;
import com.source.data.server.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("server-OrderController")
@Slf4j
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/conditionSearch")
    public Result page(OrderServerQuery page){
        log.info("分页查询订单 : {}",page);
         Pages<OrderServer_page> pages = service.selectPage(page);
        return Result.success(pages);
    }

    @GetMapping("/statistics")
    public Result statistics(){
        log.info("各个状态的订单数量统计");
        Order_statistics statistics = service.selectStatistics();
        return Result.success(statistics);
    }

    @GetMapping("/details/{id}")
    public Result details(@PathVariable("id") Long id){
        log.info("查询指定id的详细订单");
        Order_Declared orderDeclared = service.selectDeclaredOrder(id);
        return Result.success(orderDeclared);
    }

    @PutMapping("/confirm")
    public Result updateConfirm(@RequestBody Order_updateConfirm updateConfirm){
        log.info("接收修改id为:{}的订单",updateConfirm.getId());
        service.updateConfirmStatus(updateConfirm.getId());
        return Result.success();
    }

    @PutMapping("/rejection")
    public Result updateRejection(@RequestBody Order_updateRejection updateRejection){
        log.info("拒绝接收id为:{}的订单",updateRejection.getId());
        service.updateRejectionStatus(updateRejection);
        return Result.success();
    }

    @PutMapping("/cancel")
    public Result updateCancel(@RequestBody Order_updateCancel orderUpdateCancel){
        log.info("取消id为:{}的订单",orderUpdateCancel.getId());
        service.updateCancelStatus(orderUpdateCancel);
        return Result.success();
    }

    @PutMapping("/complete/{id}")
    public Result updateComplete(@PathVariable("id")Long id){
        log.info("完成订单id为:{}的订单",id);
        service.updateCompleteStatus(id);
        return Result.success();
    }

    @PutMapping("/delivery/{id}")
    public Result updateDelivery(@PathVariable("id")Long id){
        log.info("派送id为:{}的订单",id);
        service.updateDelivery(id);
        return Result.success();
    }
}
