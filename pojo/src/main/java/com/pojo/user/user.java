package com.pojo.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息实体类
 */
@Data
public class User {
    private Long id;
    private String openid;
    private String name;
    private String phone;
    private String sex;
    private String idNumber;
    private String avatar;
    private LocalDateTime createTime;
}
