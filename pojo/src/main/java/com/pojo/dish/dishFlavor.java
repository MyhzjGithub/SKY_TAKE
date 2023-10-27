package com.pojo.dish;

import lombok.Data;

import java.util.List;

/**
 * 菜品口味关系表
 */
@Data
public class dishFlavor {
    private Long id;
    private Long dishId;
    private String name;
    private List<String> value;
}
