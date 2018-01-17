package com.shares.web.home.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/4/26
 * @description
 */
@Configuration
public class AppDataConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.shares.common.dal.daointerface");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesEnv() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
