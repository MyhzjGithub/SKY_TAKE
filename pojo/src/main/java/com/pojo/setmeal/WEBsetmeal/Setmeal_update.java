package com.pojo.setmeal.WEBsetmeal;

import com.pojo.setmeal.setmealDish;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Setmeal_update  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long categoryId;
    private String description;
    private Long id;
    private String image;
    private String name;
    private Double price;
    private Integer status;
    private List<setmealDish> setmealDishes;
}
