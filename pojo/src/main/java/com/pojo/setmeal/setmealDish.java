package com.pojo.setmeal;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 套餐菜品关系类
 */
@Data
public class setmealDish {
    private Long id;
    private Long setmealId;
    private Long dishId;
    private String name;        // 菜品名称
    private BigDecimal price;   // 菜品单价
    private Integer copies;     // 菜品份数
}
