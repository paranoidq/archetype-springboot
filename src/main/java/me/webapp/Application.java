package me.webapp;

import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.WebApplicationInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {


	public static void main(String[] args) {
        try {
            SpringApplication bootstrap = new SpringApplication(Application.class);

            // Customize springboot startup banner
            bootstrap.setBannerMode(Banner.Mode.CONSOLE);
            bootstrap.setBanner(new ResourceBanner(new ClassPathResource("banner.txt")));

            // Customize shutdown hook
            bootstrap.setRegisterShutdownHook(true);
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());

            // Customize applicationContextInitailizers
            customizeApplicationContextInitializers(bootstrap);

            // start application
            bootstrap.run(args);
        } catch (Throwable throwable) {
            System.err.println("SpringBoot application startup failed");
            System.exit(-1);
        }
    }


    /**
     * Customized shutdown hook
     */
    private static class ShutdownHook extends Thread {
        @Override
        public void run() {
            // TODO

        }
    }


    private static void customizeApplicationContextInitializers(SpringApplication bootstrap) throws Exception {
        // Get customized ApplicationContextInitializer class names from META-INF
        URL url = Thread.currentThread().getContextClassLoader().getResource("META-INF/applicationContextInitializers.properties");
        UrlResource resource = new UrlResource(url);
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String[] initializerClasses = StringUtils.commaDelimitedListToStringArray( properties.getProperty("initializerClasses"));

        for (String clazz : initializerClasses) {
            Class.forName(clazz).newInstance();
            bootstrap.addInitializers();
        }
    }
}
