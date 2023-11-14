package com.pojo.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportTop10 {
    private String nameList;    // 商品名称列表，以逗号分隔
    private String numberList; // 销量列表，以逗号分隔
}
