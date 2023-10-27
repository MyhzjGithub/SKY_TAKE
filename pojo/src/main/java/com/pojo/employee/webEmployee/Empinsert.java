package com.pojo.employee.webEmployee;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Empinsert {
    private Long id;
    private String idNumber;
    private String name;
    private String phone;
    private String sex;
    private String username;
}
