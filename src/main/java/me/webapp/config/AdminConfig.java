package me.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 管理侧配置参数类
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "admin")
@PropertySource(value = "classpath:config/${spring.profiles.active}/admin.properties", encoding = "UTF-8")
public class AdminConfig {
}
