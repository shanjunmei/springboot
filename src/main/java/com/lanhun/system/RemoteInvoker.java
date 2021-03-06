package com.lanhun.system;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 远程方法实际调用者
 */
@Component
public class RemoteInvoker {

    private static Logger logger = LoggerFactory.getLogger(RemoteInvoker.class);

    private static Map<Method, RemoteMethod> methodCache = new HashMap<>();

    @Autowired
    private HttpInvoker httpInvoker;

    /**
     * 获取请求参数
     */
    private static String[] getParameterNames(Parameter[] parameters) {
        List<String> list = new ArrayList<>();
        for (Parameter parameter : parameters) {
            list.add(parameter.getName());
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 构建远程方法对象
     */
    private static RemoteMethod buildRemoteMethod(Method method) {
        RemoteMethod remoteMethod = methodCache.get(method);
        if (remoteMethod == null) {
            remoteMethod = new RemoteMethod();
            remoteMethod.setParamTypes(method.getParameterTypes());
            remoteMethod.setReturnType(method.getGenericReturnType());
            remoteMethod.setParamNames(ReflectHelper.getParameterNames(method));
            String gateway;
            String command;
            RemoteClient methodClient = method.getAnnotation(RemoteClient.class);
            gateway = methodClient.gateway();
            command = methodClient.value();
            if (isBlank(gateway) || isBlank(command)) {
                RemoteClient classClient = method.getDeclaringClass().getAnnotation(RemoteClient.class);
                if (classClient != null) {
                    if (isBlank(gateway)) {
                        gateway = classClient.gateway();
                    }
                    if (isBlank(command)) {
                        command = classClient.value();
                    }
                }
            }
            remoteMethod.setGateway(gateway);
            remoteMethod.setCommond(command);
            methodCache.put(method, remoteMethod);
        }
        return remoteMethod;
    }

    /**
     * 是否空字符串判断
     */
    public static boolean isBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 远程方法调用
     */
    public Object invoke(Method method, Object[] params) {
        RemoteMethod remoteMethod = buildRemoteMethod(method);
        return httpInvoker.invoke(remoteMethod, params);
    }
}
