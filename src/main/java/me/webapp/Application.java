package me.webapp;

import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

	public static void main(String[] args) {

        SpringApplication bootstrap = new SpringApplication(Application.class);

        // Customize springboot startup banner
        bootstrap.setBannerMode(Banner.Mode.CONSOLE);
        bootstrap.setBanner(new ResourceBanner(new ClassPathResource("banner.txt")));

        // Customize shutdown hook
        bootstrap.setRegisterShutdownHook(true);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

        // start application
        bootstrap.run(args);
    }


    /**
     * Customized shutdown hook
     */
    public static class ShutdownHook extends Thread {
        @Override
        public void run() {
            // TODO

        }
    }

}
