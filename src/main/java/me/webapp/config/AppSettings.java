package me.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
@PropertySource(value = "classpath:app.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "app")
public class AppSettings {

    private String x;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
