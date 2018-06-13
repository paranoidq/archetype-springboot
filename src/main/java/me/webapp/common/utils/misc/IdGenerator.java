package me.webapp.common.utils.misc;

import me.webapp.common.utils.number.RandomUtil;
import me.webapp.common.utils.text.EncodeUtil;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class IdGenerator {

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid2() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return RandomUtil.nextLong();
    }

    /**
     * 基于URLSafeBase64编码的SecureRandom随机生成bytes.
     */
    public static String randomBase64(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return EncodeUtil.encodeBase64UrlSafe(randomBytes);
    }
}
