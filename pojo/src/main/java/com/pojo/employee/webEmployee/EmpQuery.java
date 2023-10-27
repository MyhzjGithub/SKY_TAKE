package com.pojo.employee.webEmployee;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmpQuery {
    private String name;
    private Integer page;
    private Integer pageSize;
}
