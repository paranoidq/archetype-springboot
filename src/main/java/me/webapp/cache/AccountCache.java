package me.webapp.cache;

import me.webapp.cache.redis.RedisCache;
import me.webapp.common.annotation.Cache;
import me.webapp.common.constants.CacheKeyPrefix;
import me.webapp.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Cache
public class AccountCache {

    @Autowired
    private RedisCache cache;

    /**
     * account缓存key
     * 采用统一的前缀{@link CacheKeyPrefix#accounts}
     *
     * @param keys
     * @return
     */
    public String accountCacheKey(String... keys) {
        return cache.cacheKey(CacheKeyPrefix.accounts, keys);
    }


    /**
     * [account.email:xxx@163.com] --- [id]
     *
     * [account.id:1] - [account info]
     *
     * @param email
     * @return
     */
    public Account getAccountByEmail(String email) {
        Optional<String> idOptional = cache.get(accountCacheKey("email", email));
        return idOptional.isPresent() ? getAccountById(idOptional.get()) : null;
    }

    public Account getAccountById(String id) {
        return (Account) cache.get(accountCacheKey("id", id)).get();
    }


    public void cacheAccount(Account account) {
        String id = String.valueOf(account.getId());
        String email = account.getEmail();

        cache.set(accountCacheKey("id", id), account);
        cache.set(accountCacheKey("email", email), id);
    }


    public Account getLogin(String token) {
        Optional<String> idOptional = cache.get(accountCacheKey("login", token));
        return idOptional.isPresent() ? getAccountById(idOptional.get()) : null;
    }

    public void setLogin(Account account, String token, int timeout) {
        String id = String.valueOf(account.getId());
        cache.setEx(accountCacheKey("login", token), id, timeout);
    }

    public void logout(String token) {
        cache.del(accountCacheKey("login", token));
    }






}
