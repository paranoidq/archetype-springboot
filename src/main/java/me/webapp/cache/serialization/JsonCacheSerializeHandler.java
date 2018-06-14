package me.webapp.cache.serialization;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class JsonCacheSerializeHandler implements CacheSerializeHandler {
    @Override
    public <T extends Serializable> byte[] serialize(T object) {
        return new byte[0];
    }

    @Override
    public <T extends Serializable> Optional<T> deserialize(byte[] bytes) {
        return Optional.empty();
    }
}
