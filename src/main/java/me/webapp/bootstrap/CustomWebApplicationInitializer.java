package me.webapp.bootstrap;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Customized initializer when servlet container load spring-based application
 *
 * 注意：嵌入式web容器由于保护机制不会执行{@link WebApplicationInitializer}或{@link javax.servlet.ServletContainerInitializer}
 * 如果需要此项功能，需实现{@link org.springframework.boot.web.servlet.ServletContextInitializer}
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class CustomWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // TODO
        System.out.println("### Custom WebApplicationInitializer invoked");
    }
}
