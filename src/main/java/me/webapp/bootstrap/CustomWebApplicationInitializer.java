package me.webapp.bootstrap;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Customized initializer when servlet container load spring-based application
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class CustomWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // TODO

    }
}
