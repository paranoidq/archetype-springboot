package me.webapp.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class JacksonUtil {

    /**
     * 将对象输出为json的string格式
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJSONString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     * 将对象输出为json的string格式，并且采用默认格式化
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJSONStringPrettyFormat(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }


    /**
     * 将json字符串读取为对象实例
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T fromJSONString(String jsonString, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, clazz);
    }
}
