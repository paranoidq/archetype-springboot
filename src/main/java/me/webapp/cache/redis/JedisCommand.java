package me.webapp.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Jedis命令封装，实现类提供真实的redis操作，并通过{@link JedisExecutor}提交执行
 *
 * 子类无需处理jedis连接的close操作
 *
 * @author paranoidq
 * @since 1.0.0
 */
public interface JedisCommand<T> {

    /**
     * 执行Jedis command具体逻辑，并通过callback传递执行结果
     *
     * @param jedisCluster
     * @return T
     */
    T execute(JedisCluster jedisCluster);

}
