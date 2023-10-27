package com.pojo.employee;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 员工信息实体类
 */
@Data
public class employee {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String idNumber;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
