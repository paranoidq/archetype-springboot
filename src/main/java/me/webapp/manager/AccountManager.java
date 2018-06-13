package me.webapp.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.webapp.cache.redis.RedisCache;
import me.webapp.common.annotation.Manager;
import me.webapp.dao.AccountDao;
import me.webapp.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

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

    // guava cache
    private Cache<String, Account> loginAccounts;


    @PostConstruct
    public void init() {
        loginAccounts = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(600, TimeUnit.SECONDS)
            .build();
    }


    @Autowired
    private AccountDao accountDao;

    public Account getAccountByEmail(String email) {
        // TODO
        return accountDao.queryByEmail(email);
    }

    public void setLogin(Account account, String token) {
        // TODO
        loginAccounts.put(token, account);
    }

    public Account getLoginAccount(String token) {
        // TODO
        return loginAccounts.getIfPresent(token);
    }

    public void setLogout(String token) {
        // TODO
        loginAccounts.invalidate(token);
    }

}
