package com.pojo.Query;

import lombok.Data;
import lombok.ToString;

/**
 * 分类分页实体类
 */
@Data
@ToString
public class categoryQuery {
    private String name;    // 名称
    private Integer page;   // 页码
    private Integer pageSize;   // 显示数据量
    private Integer type;
}
