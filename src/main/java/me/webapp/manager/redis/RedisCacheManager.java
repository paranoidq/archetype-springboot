package me.webapp.manager.redis;

import me.webapp.cache.redis.RedisCache;
import me.webapp.common.annotation.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * RedisCacheManager实例
 *
 *
 * @author paranoidq
 * @since 1.0.0
 */
@Manager
@Lazy
public class RedisCacheManager {

    @Autowired
    private RedisCache redisCache;


    public String get(CacheKeyPrefix accounts, String... keys) {
        return redisCache.get(accounts, keys);
    }

    public void setEx(CacheKeyPrefix accounts, String value, int timeout, String... keys) {
        redisCache.setEx(accounts, value, timeout, keys);
    }
}
