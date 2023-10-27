package com.pojo.category;

import lombok.Data;

import java.time.LocalDateTime;

/**
 *分类管理 -- 菜品 & 套餐
 */
@Data
public class category {
    private Long id;
    private Integer type;
    private String name;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
