package com.pojo.dish.WEBdish;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分页查询菜品实体类
 */
@Data
@ToString
public class Dish_page  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;    // 菜品id
    private String name;    // 名称
    private Long categoryId;    // 分类id
    private Integer price;  // 价格
    private String image;   // 图片
    private String description; //菜品描述
    private String status;  // 菜品状态 0停售 1启售
    private LocalDateTime updateTime;   // 最后修改时间
    private String categoryName;    // 分类名称
}
