package me.webapp.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.webapp.common.utils.misc.IdGenerator;
import me.webapp.dao.AccountDao;
import me.webapp.domain.Account;
import me.webapp.exception.ServiceException;
import me.webapp.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountDao accountDao;


    // guava cache
    private Cache<String, Account> loginAccounts;

    @PostConstruct
    public void init() {
        loginAccounts = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(600, TimeUnit.SECONDS)
            .build();
    }


    @Transactional(readOnly = true)
    public String login(String email, String password) {
        Account account = accountDao.queryByEmail(email);
        if (account == null) {
            throw new ServiceException("用户不存在");
        }
        if (!account.getPassword().equals(hashPassword(password))) {
            throw new ServiceException("密码错误");
        }

        String token = IdGenerator.uuid2();
        loginAccounts.put(token, account);
        return token;
    }


    @Transactional
    public void logout(String token) {
        Account account = loginAccounts.getIfPresent(token);
        if (account == null) {
            logger.error("该token已注销");
        } else {
            loginAccounts.invalidate(token);
        }

    }

    @Override
    public void register(String email, String username, String password) throws ServiceException {

    }

    @Override
    public Account getLoginAccount(String token) throws ServiceException {
        Account account = loginAccounts.getIfPresent(token);
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
    protected static String hashPassword(String rawPassword) {
//        return EncodeUtil.encodeBase64(HashUtil.sha1(rawPassword));
        return DigestUtils.md5Hex(rawPassword);
    }

    public static void main(String[] args) {
        String s = hashPassword("88863650qw!@#");
        System.out.println(s);
    }

}
