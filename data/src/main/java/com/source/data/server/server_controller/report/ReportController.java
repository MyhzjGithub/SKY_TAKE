package com.source.data.server.server_controller.report;

import com.pojo.RESULT.Result;

import com.pojo.report.*;
import com.source.data.server.service.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/admin/report")
public class ReportController {
    @Autowired
    private ReportService service;

    @GetMapping("/turnoverStatistics")
    public Result turnoverStatistics(ReportData dataTime){
        log.info("查询:{} -- {} 之间的营业额统计",dataTime.getBegin(),dataTime.getEnd());
        ReportTurnoverStatistics turnoverStatistics = service.selectTurnoverStatistics(dataTime);
        return Result.success(turnoverStatistics);
    }

    @GetMapping("/userStatistics")
    public Result userStatistics(ReportData dataTime){
        log.info("查询:{} -- {} 之间的用户数据",dataTime.getBegin(),dataTime.getEnd());
        ReportUserStatistics userStatistics = service.selectUserStatistics(dataTime);
        return Result.success(userStatistics);
    }

    @GetMapping("/ordersStatistics")
    public Result ordersStatistics(ReportData dataTime){
        log.info("查询:{} -- {} 之间的订单数据",dataTime.getBegin(),dataTime.getEnd());
        ReportOrdersStatistics ordersStatistics = service.selectOrdersStatistics(dataTime);
        return Result.success(ordersStatistics);
    }

    @GetMapping("/top10")
    public Result top10(ReportData dataTime){
        log.info("查询:{} -- {} 之间的销量排名top10接口",dataTime.getBegin(),dataTime.getEnd());
        ReportTop10 reportTop10 = service.selectTop10(dataTime);
        return Result.success(reportTop10);
    }
}
