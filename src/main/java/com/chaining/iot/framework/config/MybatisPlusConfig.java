package com.chaining.iot.framework.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus分页功能配置类
 *
 * @program: wxstcgateway
 * @ClassName MybatisPlusConfig
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-15 21:06
 * @Version 1.0
 **/
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
