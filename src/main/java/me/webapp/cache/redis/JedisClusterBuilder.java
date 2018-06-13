package me.webapp.cache.redis;

import com.google.common.collect.Sets;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Set;

/**
 * JedisPool builder模式
 *
 * @author paranoidq
 * @since 1.0.0
 */
class JedisClusterBuilder {

    private String password;
    private int maxActive = 8;
    private int maxIdle = 8;
    private long maxWaitMillis = 5000;
    private boolean testWhileIdle = true;
    private long timeBetweenEvictionRunsMillis = 5000;
    private long minEvictableIdleTimeMillis = 10000;
    private int timeout = 5;
    private int soTimeout = 5;
    private int maxAttempts = 10;
    private List<String> clusterNodes;


    private JedisClusterBuilder(List<String> clusterNodes, String password) {
        this.password = password;
        this.clusterNodes = clusterNodes;
    }

    public static JedisClusterBuilder newBuilder(List<String> clusterNodes, String password) {
        return new JedisClusterBuilder(clusterNodes, password);
    }

    public static JedisClusterBuilder newBuilder(List<String> clusterNodes) {
        return newBuilder(clusterNodes, null);
    }

    /**
     * 构建JedisCluster实例
     * @return jedis实例
     */
    public JedisCluster build() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 配置jedis线程池数量
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        // 配置jedis线程池idle eviction
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        Set<HostAndPort> hostAndPorts = resolveClusterNodes(clusterNodes);

        JedisCluster jedisCluster;
        if (StringUtils.isEmpty(password)) {
            jedisCluster = new JedisCluster(hostAndPorts, timeout, soTimeout, maxAttempts, jedisPoolConfig);
        } else {
            jedisCluster = new JedisCluster(hostAndPorts, timeout, soTimeout, maxAttempts, password, jedisPoolConfig);
        }
        return jedisCluster;
    }

    /**
     * 解析clusterNodes字符串
     * 格式：host:port
     *
     * @param clusterNodes
     * @return
     */
    private Set<HostAndPort> resolveClusterNodes(List<String> clusterNodes) {
        Set<HostAndPort> hostAndPorts = Sets.newHashSet();
        for (String nodeString : clusterNodes) {
            String[] entry = nodeString.split(":");
            hostAndPorts.add(new HostAndPort(entry[0], Integer.parseInt(entry[1])));
        }
        return hostAndPorts;
    }

    public JedisClusterBuilder maxActive(int maxActive) {
        if (maxActive > 0) {
            this.maxActive = maxActive;
        }
        return this;
    }

    public JedisClusterBuilder maxIdle(int maxIdle) {
        if (maxIdle > 0 && maxIdle <= maxActive) {
            this.maxIdle = maxIdle;
        }
        return this;
    }

    public JedisClusterBuilder maxWaitMillis(long maxWaitMillis) {
        if (maxWaitMillis > 0) {
            this.maxWaitMillis = maxWaitMillis;
        }
        return this;
    }

    public JedisClusterBuilder timeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        if (timeBetweenEvictionRunsMillis > 0) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }
        return this;
    }

    public JedisClusterBuilder minEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        if (minEvictableIdleTimeMillis > 0) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        }
        return this;
    }

    public JedisClusterBuilder testWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
        return this;
    }

    public JedisClusterBuilder soTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
        return this;
    }

    public JedisClusterBuilder maxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }

}
