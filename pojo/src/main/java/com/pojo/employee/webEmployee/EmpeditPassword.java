package com.pojo.employee.webEmployee;

import lombok.Data;

@Data
public class EmpeditPassword {
    private Long empId;
    private String newPassword;
    private String oldPassword;

}
