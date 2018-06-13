package me.webapp.cache.redis;

import com.google.common.base.Joiner;
import me.webapp.common.annotation.NotNull;
import me.webapp.config.RedisConfig;
import me.webapp.common.constants.CacheKeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
public class RedisCache {

    @Autowired
    private RedisConfig redisConfig;

    private JedisPool jedisPool;
    private JedisCluster jedisCluster;
    private boolean clusterMode = false;

    public void init() {
        RedisConfig.Pool poolConfig = redisConfig.getJedis().getPool();
        RedisConfig.Cluster clusterConfig = redisConfig.getCluster();

        JedisBuilder builder = JedisBuilder.newBuilder()
            .host(redisConfig.getHost())
            .port(redisConfig.getPort())
            .soTimeout(redisConfig.getSoTimeout());
        if (poolConfig != null) {
            builder
                .maxIdle(poolConfig.getMaxIdle())
                .maxWaitMillis(poolConfig.getMaxWait())
                .maxActive(poolConfig.getMaxActive())
                .maxAttempts(poolConfig.getMaxAttempts())
                .testWhileIdle(poolConfig.isTestWhileIdle())
                .minEvictableIdleTimeMillis(poolConfig.getMinEvictableIdleTimeMillis())
                .timeBetweenEvictionRunsMillis(poolConfig.getTimeBetweenEvictionRunsMillis());
        }
        if (clusterConfig != null) {
            builder.clusterNodes(clusterConfig.getNodes());
        }

        // 根据配置决定是否采用cluster模式
        if (builder.isClusterMode()) {
            this.jedisCluster = builder.buildJedisCluster();
            this.clusterMode = true;
        } else {
            this.jedisPool = builder.buildJedisPool();
        }
    }


    /**
     * get
     * @param prefix
     * @param keys
     * @return
     */
    public String get(@NotNull CacheKeyPrefix prefix, String... keys) {
        String val;
        if (clusterMode) {
            val = jedisCluster.get(cacheKey(prefix, keys));
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                val = jedis.get(cacheKey(prefix, keys));
            }
        }
        return val;
    }


    /**
     * setEx
     * @param prefix
     * @param value
     * @param timeout
     * @param keys
     */
    public void setEx(@NotNull CacheKeyPrefix prefix, String value, int timeout, String... keys) {
        if (clusterMode) {
            jedisCluster.setex(cacheKey(prefix, keys), timeout, value);
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.setex(cacheKey(prefix, keys), timeout, value);
            }
        }
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
