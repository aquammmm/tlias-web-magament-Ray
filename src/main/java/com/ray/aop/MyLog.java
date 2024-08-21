package com.ray.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)        // 描述这个注解什么时候生效
@Target(ElementType.METHOD)         // 用来标准这个注解作用范围
public @interface MyLog {
}
