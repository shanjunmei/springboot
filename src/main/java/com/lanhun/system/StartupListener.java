package com.lanhun.system;


import java.lang.reflect.Field;
import java.util.Map;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/***
 * 启动监听器
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RemoteProxyFactory remoteProxyFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            ApplicationContext applicationContext = evt.getApplicationContext();
            handleRemoteClient(applicationContext);

        }
    }

    /**
     * 初始化远程调用
     */
    private void handleRemoteClient(ApplicationContext applicationContext) {
        Map<String, Object> beansCache = applicationContext.getBeansWithAnnotation(Component.class);
        beansCache.values().forEach(v -> handleRemoteClient(v));
    }

    /**
     * 初始化远程调用
     */
    private void handleRemoteClient(Object v) {
        Class<?> target = AopProxyUtils.ultimateTargetClass(v);
        if (target != null) {
            Field[] fields = ReflectHelper.findFieldByAnnotation(target, RemoteClient.class);
            for (Field field : fields) {
                Object proxy = remoteProxyFactory.getProxy(field.getType());
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                try {
                    field.set(v, proxy);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                field.setAccessible(accessible);
            }
        }
    }
}