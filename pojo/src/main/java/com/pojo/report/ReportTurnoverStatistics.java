package com.pojo.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportTurnoverStatistics {
    private String dateList;    // 日期列表，日期之间以逗号分隔
    private String turnoverList;    //  营业额列表，营业额之间以逗号分隔
}
