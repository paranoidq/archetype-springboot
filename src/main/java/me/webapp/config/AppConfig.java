package me.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
@PropertySource(value = "classpath:app.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "app")
@EnableCaching
@EnableTransactionManagement
public class AppConfig {

    // For test
    private String x;

    /** 对外开放API接口base路径*/
    private String apiOpenPath = "/open";
    /** 对管理侧开放API接口base路径*/
    private String apiAdminPath = "/admin";



    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getApiOpenPath() {
        return apiOpenPath;
    }

    public void setApiOpenPath(String apiOpenPath) {
        this.apiOpenPath = apiOpenPath;
    }

    public String getApiAdminPath() {
        return apiAdminPath;
    }

    public void setApiAdminPath(String apiAdminPath) {
        this.apiAdminPath = apiAdminPath;
    }
}
