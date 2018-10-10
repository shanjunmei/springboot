package com.lanhun.system;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Type;

/**
 * json转换
 */
public class JsonMapper {


    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json字符串
     */
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转json字节数组
     */
    public static byte[] toJson(Object obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param type
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T from(Type type, String json) {
        try {
            JavaType javaType = objectMapper.constructType(type);
            return (T) objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
