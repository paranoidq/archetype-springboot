package me.webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Order(1)
@Component
public class CustomCommandLineRunner implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        // TODO
        System.out.println("### Custom CommandLineRunner invoked");
    }
}
