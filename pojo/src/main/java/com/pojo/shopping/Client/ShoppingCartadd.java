package com.pojo.shopping.Client;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShoppingCartadd {
    private String dishFlavor;  // 菜品口味
    private Long dishId;    // 菜品id
    private Long setmealId; // 套餐id
}
