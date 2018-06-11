package me.webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在{@link org.springframework.boot.SpringApplication#run(String...)}完成之前的最后执行回调函数
 * 由于在SpringBoot应用启动完成之前执行某些操作
 *
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
