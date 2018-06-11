package me.webapp.web.support;

import me.webapp.config.AppConfig;
import me.webapp.web.support.messageConverter.TransparentJsonHttpResponseMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 自定义MVC相关配置
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class CustomWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AppConfig appConfig;

    /**
     * 添加自定义{@link HttpMessageConverter}实例
     * 可以覆盖默认实例或自定义添加顺位
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加透明的JSON格式应答报文转化类
        converters.add(0, new TransparentJsonHttpResponseMessageConverter());
    }


    /**
     * 自定义CORS相关配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(appConfig.getApiOpenPath() + "/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true).maxAge(3600);
    }

}
