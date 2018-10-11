package com.lanhun.system;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

/**
 * 反射工具类
 */
public class ReflectHelper {

    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    /**
     *

     * @Description: 获取方法参数名
     * @param  method
     * @return String[]    返回参数名称
     * @throws
     */
    public static String[] getParameterNames(Method method){
        String[] names=parameterNameDiscoverer.getParameterNames(method);
        return names;
    }
    /**
     * 获取一个类上所有拥有指定注解的字段
     */
    public static Field[] findFieldByAnnotation(Class<?> cls, Class<? extends Annotation> annotation) {
        Field[] fields = cls.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        for (Field field : fields) {
            Annotation _t = field.getAnnotation(annotation);
            if (_t != null) {
                fieldList.add(field);
            }
        }
        return fieldList.toArray(new Field[fieldList.size()]);
    }


}
