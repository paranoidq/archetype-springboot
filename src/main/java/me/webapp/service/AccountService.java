package me.webapp.service;

import me.webapp.common.utils.EncodeUtil;
import me.webapp.common.utils.HashUtil;
import me.webapp.common.utils.TokenUtil;
import me.webapp.dao.AccountDao;
import me.webapp.domain.Account;
import me.webapp.exception.ServiceException;
import me.webapp.manager.LoginUserManager;
import me.webapp.service.common.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CounterService counterService;

    @Autowired
    private LoginUserManager loginUserManager;


    public int countAccounts() throws ServiceException {
        try {
            return accountDao.queryRows();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Account> queryAccounts() throws ServiceException {
        try {
            return accountDao.queryAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
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

        String token = TokenUtil.uuid2();
        loginUserManager.recordLogin(token, account);
        counterService.increment("loginUser");
        return token;
    }


    /**
     * 将原始password做hash
     * @param rawPassword
     * @return
     */
    protected static String hashPassword(String rawPassword) {
        return EncodeUtil.encodeBase64(HashUtil.sha1(rawPassword));
    }

}
