package com.pojo.shopping;

import com.pojo.dish.DishFlavor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车类
 */
@Data
public class shoppingCart {
    private Long id;
    private String name;
    private String image;
    private Long userId;
    private Long dishId;
    private Long setmealId;
    private DishFlavor dishFlavor;
    private Integer number; // 数量
    private BigDecimal amount;  // 金额
    private LocalDateTime createTime;   // 创建时间
}
