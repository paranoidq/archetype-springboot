package me.webapp.web.open.rest;

import me.webapp.config.AppConfig;
import me.webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RestController
public class IndexController {

    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/hello")
    public String index() {
        return appConfig.getX();
    }


    @RequestMapping("/user")
    public User userTest() {
        User user = new User(1, "paranoidq", "888");
        return user;
    }

}
