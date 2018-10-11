package com.lanhun.system;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 远程方法标识
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoteClient {
    //网关
    String gateway() default "";
    //方法名
    String value() default "";

}
