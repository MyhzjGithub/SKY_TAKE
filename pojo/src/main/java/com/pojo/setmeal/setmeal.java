package com.pojo.setmeal;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐类
 */
@Data
public class setmeal  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long categoryId;
    private String name;
    private Double price;
    private Integer status; // 0停售 1启售
    private String description;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;

}
