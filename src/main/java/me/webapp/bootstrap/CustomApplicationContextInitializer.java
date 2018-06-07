package me.webapp.bootstrap;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * Customized initializer before {@link ConfigurableApplicationContext} is fully created.
 * This will invoked before {@link ConfigurableApplicationContext#refresh()}
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Order(1)
public class CustomApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //TODO
        System.out.println("### Custom ApplicationContextInitializer invoked");
    }
}
