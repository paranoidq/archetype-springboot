package me.webapp.cache.redis;

import com.google.common.base.Joiner;
import me.webapp.common.annotation.NotNull;
import me.webapp.config.RedisConfig;
import me.webapp.manager.redis.CacheKeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class RedisCache {

    @Autowired
    private RedisConfig redisConfig;

    /**
     * 注册{@link JedisExecutor}实例Bean
     *
     * 智能检测采用单机jedisPool或采用jedisCluster
     *
     * @return
     */
    @Bean
    public JedisExecutor jedisExecutor() {
        RedisConfig.Pool poolConfig = redisConfig.getJedis().getPool();
        RedisConfig.Cluster clusterConfig = redisConfig.getCluster();

        JedisClusterBuilder builder = JedisClusterBuilder.newBuilder(clusterConfig.getNodes(), redisConfig.getPassword())
            .maxIdle(poolConfig.getMaxIdle())
            .maxWaitMillis(poolConfig.getMaxWait())
            .maxActive(poolConfig.getMaxActive())
            .maxAttempts(poolConfig.getMaxAttempts())
            .soTimeout(redisConfig.getSoTimeout())
            .testWhileIdle(poolConfig.isTestWhileIdle())
            .minEvictableIdleTimeMillis(poolConfig.getMinEvictableIdleTimeMillis())
            .timeBetweenEvictionRunsMillis(poolConfig.getTimeBetweenEvictionRunsMillis());
        JedisCluster jedisCluster = builder.build();
        return new JedisExecutor(jedisCluster);
    }


    /**
     * get
     * @param prefix
     * @param keys
     * @return
     */
    public String get(@NotNull CacheKeyPrefix prefix, String... keys) {
        return jedisExecutor().execute(jedis -> jedis.get(cacheKey(prefix, keys)));
    }


    /**
     * setEx
     * @param prefix
     * @param value
     * @param timeout
     * @param keys
     */
    public void setEx(@NotNull CacheKeyPrefix prefix, String value, int timeout, String... keys) {
        jedisExecutor().execute(jedis -> jedis.setex(cacheKey(prefix, keys), timeout, value));
    }


    /**
     * 生成cacheKey
     *
     * 前置之后采用<code>.</code>分隔
     * key之间采用<code>:</code>分隔
     *
     * @param prefix 前缀
     * @param keys 组成key的列表
     * @return prefix.k1:k2:...:kn
     */
    private String cacheKey(CacheKeyPrefix prefix, String... keys) {
        String key = prefix.name();
        if (keys != null && keys.length > 0) {
            key += "." + Joiner.on(":").join(keys);
        }
        return key;
    }
}
