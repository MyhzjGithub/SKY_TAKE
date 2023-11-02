package com.pojo.dish.WEBdish;

import com.pojo.dish.DishFlavor;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 协带菜品口味表
 */
@Data
@ToString
public class Dish_public  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long categoryId;
    private String categoryName;
    private String description;
    private List<DishFlavor> flavors; // 口味表
    private Long id;
    private String image;
    private String name;
    private Double price;
    private Integer status;
    private LocalDateTime updateTime;
    private Long updateUser;
}
