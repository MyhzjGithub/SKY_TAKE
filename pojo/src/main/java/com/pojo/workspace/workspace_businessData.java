package com.pojo.workspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 工作台-- 今日运营数据
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class workspace_businessData {
    private Integer newUsers;// 新增用户数
    private Double orderCompletionRate; // 订单完成率
    private Double turnover;    // 营业额
    private Double unitPrice;   // 平均单价
    private Integer validOrderCount;    // 有效订单数
}
