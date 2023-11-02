package com.pojo.user.Client;

import lombok.Data;
import lombok.ToString;

/**
 * 客户端实体类
 */
@Data
@ToString
public class USER {
    private Long id;
    private String openid;  // 微信用户唯一标识
    private String token;   // jwt令牌
}
