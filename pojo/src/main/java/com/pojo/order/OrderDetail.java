package com.pojo.order;

import com.pojo.dish.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *订单明细表
 */
@Data
public class OrderDetail implements Serializable {
    private Long id;                // 明细id
    private String name;            // 商品名称
    private String image;           // 商品图片
    private Long orderId;           // 商品对应的订单id
    private Long dishId;            // 商品对应的菜品id
    private Long setmealId;         // 商品对应的套餐id
    private String dishFlavor;    // 商品对应的菜品口味信息
    private Integer number;         // 商品数量
    private Double amount;          // 商品单价金额
}
