package com.lanhun.system;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 远程方法调用代理创建
 */
@SuppressWarnings("unchecked")
@Component
public class RemoteProxyFactory {

    //代理缓存
    private static Map<Class, Object> proxyCache = new HashMap<>();
    @Autowired
    private RemoteInvoker remoteInvoker;

    /**
     * 创建代理
     * @param _interface
     * @param <T>
     * @return
     */
    public  <T> T createProxy(Class<T> _interface) {
        try {
            return (T) Proxy.newProxyInstance(_interface.getClassLoader(), new Class[]{_interface}, (proxy, method, args) ->
                    remoteInvoker.invoke(method, args)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从缓存获取远程调用代理，没有则创建并缓存
     * @param _interface
     * @param <T>
     * @return
     */
    public  <T> T getProxy(Class<T> _interface) {
        T intance = (T) proxyCache.get(_interface);
        if (intance == null) {
            intance = createProxy(_interface);
            proxyCache.put(_interface, _interface);
        }
        return intance;
    }
}
