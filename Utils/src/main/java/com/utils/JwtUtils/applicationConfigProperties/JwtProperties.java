package com.utils.JwtUtils.applicationConfigProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 读取配置文件中的JWT令牌信息
 */
@ConfigurationProperties(prefix = "jwt")
@Slf4j
@Configuration
@Data
public class JwtProperties {
    // server 服务端令牌信息
    private Long server_time;  // 令牌过期时间

    private String server_PasswordKey; // 令牌密钥

    private String server_token;   // 令牌名称

    // client客户端
    private Long client_time; // 令牌过期时间

    private String client_PasswordKey; // 令牌密钥

    private String client_token;  // 令牌名称

}
