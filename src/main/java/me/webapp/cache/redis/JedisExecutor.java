package me.webapp.cache.redis;

import me.webapp.exception.CacheException;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class JedisExecutor {
    private JedisCluster jedisCluster;

    public JedisExecutor(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    /**
     * Command模式，调用JedisCommand执行redis处理，并做清理工作
     * 具体的redis处理逻辑在JedisCommand的实现中
     *
     * <p>
     *     采用该模式可以避免忘记归还jedis到资源池导致的泄露问题
     * </p>
     *
     * 经过试验，也可以采用CGLib动态代理，但是由于jedis处理的内部会相互调用，因此动态代理要对方法进行判断，避免多次被代理和循环调用的问题，且存在性能隐患
     * 因此不如采用command模式简洁高效
     *
     * @param jedisCommand
     * @param <T>
     * @return
     */
    public <T> T execute(JedisCommand<T> jedisCommand) {
        try {
            return jedisCommand.execute(jedisCluster);
        } catch (Exception e) {
            throw new CacheException("cache execute exception", e);
        } finally {
            try {
                jedisCluster.close();
            } catch (IOException e) {
                throw new CacheException("close jedis cluster exception", e);
            }
        }
    }
}
