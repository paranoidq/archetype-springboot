package me.webapp.web.open.rest;

import me.webapp.config.AppConfig;
import me.webapp.domain.Account;
import me.webapp.service.AccountService;
import me.webapp.web.common.ApiResponse;
import me.webapp.web.common.MediaTypes;
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
    private AccountService accountService;

    @RequestMapping("/hello")
    public String index() {
        return appConfig.getX();
    }


    @RequestMapping(value = "/user", produces = MediaTypes.JSON_UTF_8)
    public List<Account> userTest() {
        List<Account> accounts = accountService.queryAccounts();
        return accounts;
    }

    @RequestMapping(value = "/userRows", produces = MediaTypes.JSON_UTF_8)
    public ApiResponse userRowsTest() {
        int rows = accountService.countAccounts();
        return ApiResponse.createOk(rows);
    }


}
