package com.pojo.order;

import com.pojo.dish.dishFlavor;
import lombok.Data;

import java.math.BigDecimal;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;

/**
 *订单明细表
 */
@Data
public class orderDetail {
    private Long id;    // 明细id
    private String name;    // 商品名称
    private String image;   // 商品图片
    private Long orderId;   // 商品对应的订单id
    private Long dishId;    // 商品对应的菜品id
    private Long setmealId; // 商品对应的套餐id
    private dishFlavor dishFlavor;    // 商品对应的菜品口味信息
    private Integer number; // 商品数量
    private BigDecimal amount;  // 商品单价金额
}