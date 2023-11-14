package com.pojo.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportOrdersStatistics {
    private String dateList;    //日期列表，以逗号分隔
    private Double orderCompletionRate; // 订单完成率
    private String orderCountList;  //订单数列表，以逗号分隔
    private Integer totalOrderCount; // 订单总数
    private Integer validOrderCount; // 有效订单数
    private String validOrderCountList; // 有效订单数列表，以逗号分隔
}
