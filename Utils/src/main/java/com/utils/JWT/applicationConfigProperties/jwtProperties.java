package com.utils.JWT.applicationConfigProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中的JWT令牌信息
 */
@ConfigurationProperties(prefix = "jwt")
@Slf4j
@Component
@Data
public class jwtProperties {
    private Long time;  // 令牌过期时间
    private String PasswordKey; // 令牌密钥
    private String token;   // 令牌名称
}
