package com.pojo.workspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class workspace_overviewSetmeals {
    private Integer discontinued;   // 已停售套餐数量
    private Integer sold;   // 已启售套餐数量
}
