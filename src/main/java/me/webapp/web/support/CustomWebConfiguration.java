package me.webapp.web.support;

import me.webapp.web.support.messageConverter.TransparentJsonHttpResponseMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Configuration
public class CustomWebConfiguration implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加透明的JSON格式应答报文转化类
        converters.add(0, new TransparentJsonHttpResponseMessageConverter());
    }
}
