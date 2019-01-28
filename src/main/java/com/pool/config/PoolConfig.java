package com.pool.config;

import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PoolConfig {

    @Bean
    @ConfigurationProperties(prefix = "object.pool.simple")
    public CommonsPool2TargetSource simpleObjectPool() {
        CommonsPool2TargetSource pool = new CommonsPool2TargetSource();
        pool.setTargetBeanName("simpleService");
        return pool;
    }
}