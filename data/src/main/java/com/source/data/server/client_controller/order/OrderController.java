package com.source.data.server.client_controller.order;

import com.pojo.Page.Pages;
import com.pojo.Query.OrderClientQuery;
import com.pojo.RESULT.Result;
import com.pojo.order.WEBorder.Order_message;
import com.pojo.order.WEBorder.OrderClient_page;
import com.pojo.order.WEBorder.Order_submit;
import com.source.data.server.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("client-OrderController")
@Slf4j
@RequestMapping("/user/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/submit")
    public Result submit(@RequestBody Order_submit orderSubmit){
        log.info("有用户下单");
        Order_message message = service.insert(orderSubmit);
        return Result.success(message);
    }

    @GetMapping("/historyOrders")
    public Result historyOrders(OrderClientQuery query){
        log.info("查询当前用户的历史订单");
        Pages<OrderClient_page> pages = service.selectOrders(query);
        return Result.success(pages);
    }

    @GetMapping("/orderDetail/{id}")
    public Result getOrderID(@PathVariable("id") Long id){
        log.info("查单id为:{} 的订单信息",id);
        OrderClient_page orderPage = service.selectOrderID(id);
        return Result.success(orderPage);
    }

    @PutMapping("/cancel/{id}")
    public Result cancel(@PathVariable("id") Long id){
        log.info("取消id为:{} 的订单",id);
        service.updateOrderId(id);
        return Result.success();
    }

    @PostMapping("/repetition/{id}")
    public Result repetition(@PathVariable("id") Long id){
        log.info("再来一单id为:{}的订单",id);
        service.openOrder(id);
        return Result.success();
    }

    @GetMapping("/reminder/{id}")
    public Result reminder(@PathVariable("id") Long id){
        log.info("催促id为:{}的订单",id);
        service.reminder(id);
        return Result.success();
    }
}
