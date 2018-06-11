package me.webapp.bootstrap;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 在嵌入式web容器中，无法使用{@link org.springframework.web.WebApplicationInitializer}实现类
 * 需要注册一个实现了{@link ServletContextInitializer}的bean来作为alternative方案
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class CustomServletContextInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("### ServletContextInitializer invoked");
    }
}
