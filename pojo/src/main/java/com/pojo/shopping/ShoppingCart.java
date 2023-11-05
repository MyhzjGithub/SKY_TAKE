package com.pojo.shopping;

import com.pojo.dish.DishFlavor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购物车类
 */
@Data
public class ShoppingCart {
    private Long id;
    private String name;
    private String image;
    private Long userId;
    private Long dishId;
    private Long setmealId;
    private String dishFlavor;
    private Integer number; // 数量
    private Double amount;  // 金额
    private LocalDateTime createTime;   // 创建时间
}
