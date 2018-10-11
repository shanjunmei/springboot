package com.lanhun.system;


import java.lang.reflect.Field;
import java.util.Map;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/***
 * 启动监听器
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            ApplicationContext applicationContext = evt.getApplicationContext();
            handleRemoteClient(applicationContext);

        }
    }

    /**
     * 初始化远程调用
     * @param applicationContext
     */
    private void handleRemoteClient(ApplicationContext applicationContext) {
        Map<String, Object> beansCache = applicationContext.getBeansWithAnnotation(Component.class);
        beansCache.values().forEach(v -> {
            Class<?> target = AopProxyUtils.ultimateTargetClass(v);
            if (target != null) {
                Field[] fields = ReflectHelper.findFieldByAnnotation(target, RemoteClient.class);
                for (Field field : fields) {
                    Object proxy = RemoteProxyFactory.getProxy(field.getType());
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
        });
    }


}