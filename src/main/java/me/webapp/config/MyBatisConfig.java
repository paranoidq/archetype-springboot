package me.webapp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@MapperScan(basePackages = "me.webapp.dao")
public class MyBatisConfig {

}
