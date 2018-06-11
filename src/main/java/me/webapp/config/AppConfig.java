package me.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 开放接口侧配置参数类
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "app")
@PropertySource(value = "classpath:config/${spring.profiles.active}/app.properties", encoding = "UTF-8")
public class AppConfig {

    // For test
    private String x;

    /** 对外开放API接口base路径*/
    private String apiOpenPath = "/api";
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
