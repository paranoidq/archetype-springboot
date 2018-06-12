package me.webapp.web.open.rest;

import me.webapp.config.AppConfig;
import me.webapp.domain.User;
import me.webapp.service.UserService;
import me.webapp.web.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RestController
public class IndexController {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String index() {
        return appConfig.getX();
    }


    @RequestMapping("/user")
    public List<User> userTest() {
        List<User> users = userService.queryAll();
        return users;
    }

    @RequestMapping("/userRows")
    public ApiResponse userRowsTest() {
        int rows = userService.queryRows();
        return ApiResponse.createOk(rows);
    }


}
