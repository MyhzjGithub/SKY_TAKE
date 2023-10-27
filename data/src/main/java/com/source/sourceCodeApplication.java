package com.source;

import com.utils.ALIYUNYUN.applicationConfigProperties.AliOssProperties;
import com.utils.JWT.applicationConfigProperties.jwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// @ServletComponentScan	// 开启对Filter的支持
@EnableTransactionManagement	// 开启事务的支持
@ComponentScan(basePackages = {"com.source","com.utils.ALIYUNYUN","com.utils.JWT"})	// 需要先声明主启动类下的包 在注入别的包
public class sourceCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(sourceCodeApplication.class, args);
	}

}
