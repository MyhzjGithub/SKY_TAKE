package com.pojo.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportUserStatistics {
    private String dateList;    // 日期 ，以逗号分隔
    private String newUserList; // 当日新增 ，以逗号分隔
    private String totalUserList;   // 总用户 ，以逗号分隔
}
