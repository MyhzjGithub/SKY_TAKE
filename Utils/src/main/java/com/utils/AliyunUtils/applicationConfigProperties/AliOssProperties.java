package com.utils.AliyunUtils.applicationConfigProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件的aliyun.oss
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
