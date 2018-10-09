package com.lanhun.system;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RemoteClientLoader {

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
