package com.pojo.setmeal.WEBsetmeal;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Setmeal_data implements Serializable {
    private String name;    // 菜品名称
    private Integer copies; // 份数
    private String description; // 菜品描述
    private String image; // 菜品图片路径
}
