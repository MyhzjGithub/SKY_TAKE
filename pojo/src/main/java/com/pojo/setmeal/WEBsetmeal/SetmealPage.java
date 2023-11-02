package com.pojo.setmeal.WEBsetmeal;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SetmealPage  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long categoryId;
    private String name;
    private Double price;
    private Integer status;
    private String description;
    private String image;
    private LocalDateTime updateTime;
    private String categoryName;
}
