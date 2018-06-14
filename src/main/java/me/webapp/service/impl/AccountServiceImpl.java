package me.webapp.service.impl;

import me.webapp.common.utils.misc.IdGenerator;
import me.webapp.common.utils.text.EncodeUtil;
import me.webapp.common.utils.text.HashUtil;
import me.webapp.config.AppConfig;
import me.webapp.domain.Account;
import me.webapp.exception.ServiceException;
import me.webapp.manager.AccountManager;
import me.webapp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service层为上层提供业务逻辑，但不关注具体的数据存储在缓存或是数据库中
 * 因此，提炼出Manager层来抽象这一功能
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private AccountManager accountManager;


    @Transactional(readOnly = true)
    public String login(String email, String password) {
        Account account = accountManager.getAccountByEmail(email);
        if (account == null) {
            throw new ServiceException("用户不存在");
        }
        if (!account.getPassword().equals(hashPassword(password))) {
            throw new ServiceException("密码错误");
        }

        String token = IdGenerator.uuid2();
        // 超时时间可配置
        accountManager.setLogin(account, token, appConfig.getLoginTokenTimeout());
        return token;
    }


    @Transactional(readOnly = true)
    public void logout(String token) {
        Account account = accountManager.getLogin(token);
        if (account == null) {
            logger.error("该token已注销");
        } else {
            accountManager.setLogout(token);
        }
    }

    @Transactional
    @Override
    public void register(String email, String username, String password) throws ServiceException {

    }

    @Override
    public Account getLoginAccount(String token) throws ServiceException {
        Account account = accountManager.getLogin(token);
        if (account == null) {
            throw new ServiceException("用户未登录");
        }
        return account;
    }

    @Override
    public String resetPassword(String email, String username, String newPassword) throws ServiceException {
        return null;
    }


    /**
     * 将原始password做hash
     *
     * @param rawPassword
     * @return
     */
    private static String hashPassword(String rawPassword) {
        return EncodeUtil.encodeBase64(HashUtil.sha1(rawPassword));
    }

    public static void main(String[] args) {
        String s = hashPassword("88863650qw!@#");
        System.out.println(s);
    }

}
