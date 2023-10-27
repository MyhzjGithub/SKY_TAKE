package com.pojo.dish;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 菜品实体类
 */
@Data
public class dish {
    private Long id;
    private String name;
    private Long categoryId;
    private Double price;
    private String image;
    private String description;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
