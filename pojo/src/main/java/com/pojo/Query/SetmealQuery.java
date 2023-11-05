package com.pojo.Query;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString
public class SetmealQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long categoryId;    // 分类名称
    private String name;    // 菜品名称
    private Integer page;   // 页码
    private Integer pageSize;   // 展示数据数量
    private Integer status; // 状态
}
