package com.source.data.server.service.report.impl;

import com.pojo.order.Order;
import com.pojo.order.OrderDetail;
import com.pojo.report.*;
import com.source.data.server.service.order.OrderDetailService;
import com.source.data.server.service.order.OrderService;
import com.source.data.server.service.report.ReportService;
import com.source.data.server.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;

@Service
public class ReportClass implements ReportService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private userService userService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public ReportTurnoverStatistics selectTurnoverStatistics(ReportData dataTime) {
        LocalDate begin = dataTime.getBegin();  // 起始时间
        LocalDate end = dataTime.getEnd();  // 结束时间
        LocalDate current = begin;
        StringBuilder dateList = new StringBuilder();
        StringBuilder turnoverList = new StringBuilder();
        while (!current.isAfter(end)){  // 遍历时间 依次表示起始时间与结束时间之间
            dateList.append(current).append(",");   // 添加日期
            LocalDateTime dateTimeMIN = LocalDateTime.of(current, LocalTime.MIN);   // 获取当天的起始最小时间
            LocalDateTime dateTimeMAX = LocalDateTime.of(current, LocalTime.MAX);   // 获取当天的结束最大时间
            Double turnover =  orderService.getDeclaredTurnover(dateTimeMIN,dateTimeMAX);
            turnoverList.append(turnover).append(",");  // 添加营业额
            current = current.plusDays(1);
        }
        return new ReportTurnoverStatistics(dateList.toString(),turnoverList.toString());
    }

    @Override
    public ReportUserStatistics selectUserStatistics(ReportData dataTime) {
        LocalDate begin = dataTime.getBegin();  // 起始时间
        LocalDate end = dataTime.getEnd();  // 结束时间
        LocalDate current = begin;
        StringBuilder dateList = new StringBuilder();
        StringBuilder newUserList = new StringBuilder();
        StringBuilder totalUserList = new StringBuilder();
        while (!current.isAfter(end)){  // 遍历时间 依次表示起始时间与结束时间之间
            dateList.append(current).append(",");   // 添加日期
            LocalDateTime dateTimeMIN = LocalDateTime.of(current, LocalTime.MIN);   // 获取当天的起始最小时间
            LocalDateTime dateTimeMAX = LocalDateTime.of(current, LocalTime.MAX);   // 获取当天的结束最大时间
            Integer turnover =  userService.getDeclareduser(dateTimeMIN,dateTimeMAX);   // 获取当天新增用户数
            newUserList.append(turnover).append(",");  // 添加用户
            Integer totalUser = userService.getUserCount();
            totalUserList.append(totalUser).append(",");
            current = current.plusDays(1);
        }
        return new ReportUserStatistics(dateList.toString(), newUserList.toString(), totalUserList.toString());
    }

    @Override
    public ReportOrdersStatistics selectOrdersStatistics(ReportData dataTime) {
        LocalDate begin = dataTime.getBegin();  // 起始时间
        LocalDate end = dataTime.getEnd();  // 结束时间
        LocalDate current = begin;
        LocalDateTime begins = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime ends = LocalDateTime.of(end, LocalTime.MAX);
        Double declaredOrderCompletionRate = orderService.getDeclaredOrderCompletionRate(begins, ends);  // 订单完成率
        Integer count = orderService.selectCount(); // 订单总数
        Integer declaredValidOrderCount = orderService.getDeclaredValidOrderCount(begins, ends);    // 有效订单数
        StringBuilder dateList = new StringBuilder();   //日期列表
        StringBuilder orderCountList = new StringBuilder(); //订单数列表
        StringBuilder validOrderCountList = new StringBuilder(); // 有效订单数列表
        while (!current.isAfter(end)){  // 遍历时间 依次表示起始时间与结束时间之间
            dateList.append(current).append(",");   // 添加日期
            LocalDateTime dateTimeMIN = LocalDateTime.of(current, LocalTime.MIN);   // 获取当天的起始最小时间
            LocalDateTime dateTimeMAX = LocalDateTime.of(current, LocalTime.MAX);   // 获取当天的结束最大时间
            Integer declaredOrder = orderService.getDeclaredCount(dateTimeMIN,dateTimeMAX);    // 当天订单总数
            orderCountList.append(declaredOrder).append(",");
            Integer declaredValidOrder = orderService.getDeclaredValidCount(dateTimeMIN,dateTimeMAX);    // 当天有效订单总数
            validOrderCountList.append(declaredValidOrder).append(",");
            current = current.plusDays(1);
        }
        return new ReportOrdersStatistics(dateList.toString(), declaredOrderCompletionRate,
                orderCountList.toString(), count, declaredValidOrderCount, validOrderCountList.toString());
    }

    @Override
    public ReportTop10 selectTop10(ReportData dataTime) {
        LocalDate begin = dataTime.getBegin();  // 起始时间
        LocalDate end = dataTime.getEnd();  // 结束时间
        LocalDateTime begins = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime ends = LocalDateTime.of(end, LocalTime.MAX);
        List<Order> orderList =  orderService.selectOrder(begins,ends); // 获取指定日期之间的全部订单
        Map<String,Integer> map = new HashMap<>();  // 统计订单中菜品的// 销量
        for (Order order : orderList) {
            List<OrderDetail> details = orderDetailService.getOrderId(order.getId());   // 当前订单的关系表
            for (OrderDetail detail : details) {
                if (map.containsKey(detail.getName())){
                    map.put(detail.getName(), map.get(detail.getName()) + 1);
                }else {
                    map.put(detail.getName(), 1);
                }
            }
        }
        // map集合中就统计好了 指定时间中的菜品的销量
        StringBuilder nameList = new StringBuilder();   // 商品名称列表，以逗号分隔
        StringBuilder numberList = new StringBuilder(); // 销量列表，以逗号分隔
        if (map.size() == 0){   // 表示一个月内没有菜品销售
            return null;
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());    // 将map集合变为单例list集合
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());   // 以值尾标准进行倒序排列  只取销量最高的
        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            if (count >= 10) {
                break;
            }
            nameList.append(entry.getKey()).append(",");
            numberList.append(entry.getValue()).append(",");
            count++;
        }
        return new ReportTop10(nameList.toString(), numberList.toString());
    }
}
