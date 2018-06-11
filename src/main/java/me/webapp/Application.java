package me.webapp;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableSwagger2Doc
@EnableCaching
@EnableTransactionManagement
public class Application {


	public static void main(String[] args) {
        try {
            SpringApplication bootstrap = new SpringApplication(Application.class);

            // 设置添加命令行参数到Environment中
            bootstrap.setAddCommandLineProperties(true);

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
            throwable.printStackTrace();
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
            ApplicationContextInitializer customizeInitializer = (ApplicationContextInitializer) Class.forName(clazz).newInstance();
            bootstrap.addInitializers(customizeInitializer);
        }
    }
}
