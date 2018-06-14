package me.webapp.cache.support;

import com.google.common.base.Joiner;
import me.webapp.common.constants.CacheKeyPrefix;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class CacheUtil {

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
    public static String generateCacheKey(CacheKeyPrefix prefix, String... keys) {
        String key = prefix.name();
        if (keys != null && keys.length > 0) {
            key += "." + Joiner.on(":").join(keys);
        }
        return key;
    }
}
