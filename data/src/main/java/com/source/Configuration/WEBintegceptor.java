package com.source.Configuration;


import com.source.interceptor.client.clientjwtInterceptor;
import com.source.interceptor.server.serverjwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * WEB组件配置
 */
@Configuration
@Slf4j
public class WEBintegceptor extends WebMvcConfigurationSupport {
    @Autowired
    private serverjwtInterceptor serverjwtInterceptor;
    @Autowired
    private clientjwtInterceptor clientjwtInterceptor;
    /**
     * 配置自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(serverjwtInterceptor)   // 配置服务端拦截器
                .addPathPatterns("/admin/**")   // 拦截server所有的请求
                .excludePathPatterns("/admin/employee/login");  // 放行登入接口
        registry.addInterceptor(clientjwtInterceptor)   // 配置客户端拦截器
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login")
                .excludePathPatterns("/user/shop/status");  // 放行获取店铺状态的
    }
    /**
     * 设置静态资源映射
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 扩展SpringMVC消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器");
        // 创建消息转换器对象
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        // 消息转换器中设置一个对象转换器 可以将java对象转为json格式的字符串
        mappingJackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        // 将消息转换器添加mvc中 并设置优先级为0
        converters.add(0,mappingJackson2HttpMessageConverter);
    }
}
