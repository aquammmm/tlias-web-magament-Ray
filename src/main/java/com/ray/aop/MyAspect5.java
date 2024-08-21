package com.ray.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class MyAspect5 {
    //针对list方法、delete方法进行前置通知和后置通知
    //前置通知

    @Pointcut("@annotation(com.ray.aop.MyLog)")
    private void pt(){};

    @Before("pt()")
    public void before(){
        log.info("MyAspect5 -> before ...");
    }

    //后置通知
    @After("@annotation(com.ray.aop.MyLog)")
    public void after(){
        log.info("MyAspect5 -> after ...");
    }

}
