package me.webapp.web.open.rest;

import me.webapp.domain.Account;
import me.webapp.service.AccountService;
import me.webapp.web.common.ApiResponse;
import me.webapp.web.common.MediaTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/accounts", produces = MediaTypes.JSON_UTF_8)
public class AccountEndpoint {

    @Autowired
    private AccountService accountService;


    /**
     * 获取登录账户信息
     * @param token
     * @return
     */
    @GetMapping(value = "/{token}")
    public Account getLoginAccount(@PathVariable("token") String token) {
        Account account = accountService.getLoginAccount(token);
        return account;
    }

    /**
     * 账户登录
     * @param email
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public ApiResponse login(@RequestParam("email") String email, @RequestParam("password") String password) {
        String token = accountService.login(email, password);
        return ApiResponse.createOk(token);
    }


    /**
     * 账户注销
     * @param token
     * @return
     */
    @PostMapping(value = "/logout")
    public ApiResponse logout(@RequestParam("token") String token) {
        accountService.logout(token);
        return ApiResponse.createOk("注销成功");
    }

}
