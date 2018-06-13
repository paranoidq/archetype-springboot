package me.webapp.web.open.rest;

import me.webapp.manager.cache.CacheKeyPrefix;
import me.webapp.manager.cache.RedisCacheManager;
import me.webapp.web.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@RestController
public class RedisTest {

    @Autowired
    private RedisCacheManager cacheManager;

    @RequestMapping("/redis")
    public ApiResponse test() {
        cacheManager.setEx(CacheKeyPrefix.accounts, "123", 1000, "paranoidq", "password");
        String password = cacheManager.get(CacheKeyPrefix.accounts, "paranoidq", "password");
        return ApiResponse.createOk(password);
    }

}
