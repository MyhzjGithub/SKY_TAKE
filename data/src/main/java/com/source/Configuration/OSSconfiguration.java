package com.source.Configuration;


import com.utils.AliyunUtils.applicationConfigProperties.AliOssProperties;
import com.utils.AliyunUtils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置阿里云文件上传
 */
@Configuration
@Slf4j
public class OSSconfiguration {
    @Autowired
    AliOssProperties aliOssProperties;


    @Bean
    public AliOssUtil aliOssUtil(){
        log.info("配置阿里云文件上传...");
        return new AliOssUtil();
    }
}
