package com.ray.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
@Order(1)
public class MyAspect4 {

    // 引用MyAspect切面类中的切入点表达式
    @Before("execution(* com.ray.service.*.*(..))")
    public void before(){
        log.info("MyAspect4 -> before ...");
    }

    //后置通知
    @After("execution(* com.ray.service.*.*(..))")
    public void after() {
        log.info("MyAspect4 -> after ...");
    }

}
