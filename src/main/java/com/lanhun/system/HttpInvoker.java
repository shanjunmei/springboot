package com.lanhun.system;

import java.lang.reflect.Type;

/**
 * http 请求
 */
public class HttpInvoker {

    /**
     * http post 请求，带返回值类型
     * @param url
     * @param returnType
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T post(String url, Type returnType, Object params) {
        //request
        String body="";
        return buildResponse(returnType,body);

    }

    public static <T> T buildResponse(Type type,String body){
        return null;
    }
}
