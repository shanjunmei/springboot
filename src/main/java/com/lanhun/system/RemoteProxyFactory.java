package com.lanhun.system;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 远程方法调用代理创建
 */
@SuppressWarnings("unchecked")
public class RemoteProxyFactory {

    //代理缓存
    private static Map<Class, Object> proxyCache = new HashMap<>();

    /**
     * 创建代理
     */

    public static <T> T createProxy(Class<T> _interface) {
        try {
            return (T) Proxy.newProxyInstance(_interface.getClassLoader(), new Class[]{_interface}, (proxy, method, args) ->
                    RemoteInvoker.invoke(method, args)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从缓存获取远程调用代理，没有则创建
     */
    public static <T> T getProxy(Class<T> _interface) {
        T intance = (T) proxyCache.get(_interface);
        if (intance == null) {
            intance = createProxy(_interface);
            proxyCache.put(_interface, _interface);
        }
        return intance;
    }
}
