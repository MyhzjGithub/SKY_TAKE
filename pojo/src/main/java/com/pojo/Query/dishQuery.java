package com.pojo.Query;

import lombok.Data;
import lombok.ToString;

/**
 * 菜品分页实体类
 */
@Data
@ToString
public class dishQuery {
    private Long categoryId;    // 分类名称
    private String name;    // 菜品名称
    private Integer page;   // 页码
    private Integer pageSize;   // 展示数据数量
    private Integer status; // 状态
}
