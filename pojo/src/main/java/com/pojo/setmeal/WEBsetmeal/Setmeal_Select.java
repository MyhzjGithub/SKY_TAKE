package com.pojo.setmeal.WEBsetmeal;

import com.pojo.setmeal.setmealDish;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class Setmeal_Select  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long categoryId;
    private String categoryName;
    private String description;
    private Long id;
    private String image;
    private String name;
    private Double price;
    private List<setmealDish> setmealDishes;
    private Integer status;
    private LocalDateTime updateTime;
}
