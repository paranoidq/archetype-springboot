package me.webapp.common.annotation.cache;

import me.webapp.common.constants.CacheKeyPrefix;

import java.lang.annotation.*;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface CacheUpdate {

    CacheKeyPrefix prefix();

    String[] keys();

    String[] fields() default {};
}
