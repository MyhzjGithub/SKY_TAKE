package com.pojo.setmeal;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐类
 */
@Data
public class setmeal {
    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal price;
    private Integer status; // 0停售 1启售
    private String description;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;

}
