package com.pojo.dish;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜品口味关系表
 */
@Data
public class DishFlavor {
    private Long id;
    private Long dishId;
    private String name;
    @JsonProperty("value")  // 实体映射 将value元素映射到value_data数据上
    private String value_data;  // value是数据库关键字
}
