package me.webapp.cache.redis;

import com.google.common.base.Joiner;
import me.webapp.cache.support.CacheSerializeHandler;
import me.webapp.cache.support.DefaultCacheSerializeHandler;
import me.webapp.config.RedisConfig;
import me.webapp.common.constants.CacheKeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Optional;

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

    private CacheSerializeHandler cacheSerializeHandler;


    @PostConstruct
    protected void init() {
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

        // 设置序列化处理器
        this.cacheSerializeHandler = new DefaultCacheSerializeHandler();
    }


    /**
     * get
     * @param key
     * @return
     */
    public <T extends Serializable> Optional<T> get(String key) {
        byte[] keyBytes = key.getBytes();

        byte[] valueBytes;
        if (clusterMode) {
            valueBytes = jedisCluster.get(keyBytes);
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                valueBytes = jedis.get(keyBytes);
            }
        }
        return cacheSerializeHandler.deserialize(valueBytes);
    }


    /**
     * set
     * @param key
     * @param value
     * @param <T>
     */
    public <T extends Serializable> void set(String key, T value) {
        byte[] keyBytes = key.getBytes();
        byte[] valueBytes = cacheSerializeHandler.serialize(value);

        if (clusterMode) {
            jedisCluster.set(keyBytes, valueBytes);
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.set(keyBytes, valueBytes);
            }
        }
    }


    /**
     * setEx
     * @param key
     * @param value
     * @param timeout
     */
    public <T extends Serializable> void setEx(String key, T value, int timeout) {
        byte[] keyBytes = key.getBytes();
        byte[] valueBytes = cacheSerializeHandler.serialize(value);

        if (clusterMode) {
            jedisCluster.setex(keyBytes, timeout, valueBytes);
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.setex(keyBytes, timeout, valueBytes);
            }
        }
    }


    /**
     * hget
     * @param key
     * @param field
     * @param <T>
     * @return
     */
    public <T extends Serializable> Optional<T> hget(String key, String field) {
        byte[] keyBytes = key.getBytes();
        byte[] fieldBytes = field.getBytes();

        byte[] valueBytes;
        if (clusterMode) {
            valueBytes = jedisCluster.hget(keyBytes, fieldBytes);
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                valueBytes = jedis.hget(keyBytes, fieldBytes);
            }
        }
        return cacheSerializeHandler.deserialize(valueBytes);
    }


    /**
     * hset
     * @param key
     * @param field
     * @param value
     * @param <T>
     */
    public <T extends Serializable> void hset(String key, String field, T value) {
        byte[] keyBytes = key.getBytes();
        byte[] fieldBytes = field.getBytes();
        byte[] valueByte = cacheSerializeHandler.serialize(value);

        if (clusterMode) {
            jedisCluster.hset(keyBytes, fieldBytes, valueByte);
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.hset(keyBytes, fieldBytes, valueByte);
            }
        }
    }


    /**
     * del
     * @param key
     */
    public void del(String key) {
        byte[] keyBytes = key.getBytes();

        if (clusterMode) {
            jedisCluster.del(keyBytes);
        } else {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.del(keyBytes);
            }
        }
    }


}
