package com.pojo.category.WEBcategory;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Catinsert {
    private Long id;
    private String name;
    private Integer sort;
    private Integer type;
}
