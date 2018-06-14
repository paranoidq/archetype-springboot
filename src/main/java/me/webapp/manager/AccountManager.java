package me.webapp.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.webapp.cache.AccountCache;
import me.webapp.common.annotation.Manager;
import me.webapp.common.annotation.cache.CacheFirst;
import me.webapp.common.constants.CacheKeyPrefix;
import me.webapp.dao.AccountDao;
import me.webapp.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * AccountManager实例
 * <p>
 * manager层介于service层和dao层之间，service层负责业务逻辑，但例如数据从缓存还是数据库中来，
 * 则不是service层应该关心的事情。因此，提炼manager层负责处理这种与业务无关的整合逻辑。
 * <p>
 *
 * 从设计思想上，manager类似于一个中间人的角色，向service层屏蔽dao层或cache层的处理细节
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Manager
public class AccountManager {

    private static final Logger logger = LoggerFactory.getLogger(AccountManager.class);

    // guava cache
    private Cache<String, Account> loginAccounts;


    @PostConstruct
    public void init() {
        loginAccounts = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(600, TimeUnit.SECONDS)
            .build();
    }


    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountCache accountCache;


    /**
     * TODO: 提供了最简单的缓存控制，没有考虑到更复杂的情况，例如缓存雪崩、缓存穿透等问题
     *
     * 没有防止重复登录的机制
     * redis key值设计不合理
     *
     *
     * @param email
     * @return
     */
    public Account getAccountByEmail(String email) {
        Account account = accountCache.getAccountByEmail(email);
        if (account == null) {
            account = accountDao.queryByEmail(email);
            if (account != null) {
                accountCache.cacheAccount(account);
            } else {
                // TODO: 缓存穿透问题
            }
            return account;
        }
        logger.info("缓存命中");
        return account;
    }

    public void setLogin(Account account, String token, int timeout) {
        accountCache.setLogin(account, token, timeout);
    }

    public Account getLogin(String token) {
        return accountCache.getLogin(token);
    }

    public void setLogout(String token) {
        accountCache.logout(token);
    }

}
