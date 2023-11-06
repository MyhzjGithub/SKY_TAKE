package com.pojo.Query;

import lombok.Data;
import lombok.ToString;

/**
 * 员工分页实体类
 */
@Data
@ToString
public class EmployeeQuery {
    private String name;    // 名称
    private Integer page;   // 页码
    private Integer pageSize;   // 显示数据量
}
