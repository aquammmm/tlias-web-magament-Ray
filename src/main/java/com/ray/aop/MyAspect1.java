package com.ray.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect     // 为测试MyAspect2,3,4 将其注释
public class MyAspect1 {

    @Pointcut("execution(* com.ray.service.impl.DeptServiceImpl.*(..))")
    public void pt(){};

    @Before("pt()")
    private void before(){  //  当外部其他切面类中也要引用当前类中的切入点表达式，就需要把private改为public
        log.info("before...");
    }

    @Around("pt()")
    private Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before...");

        // 调用目标对象的原始方法执行
        Object result = proceedingJoinPoint.proceed();

        log.info("around after...");
        return result;
    }

    @After("pt()")
    private void after(){
        log.info("after...");
    }

    @AfterReturning("pt()")
    private void afterReturning(){
        log.info("afterReturning...");
    }

    @AfterThrowing("pt()")
    private void afterhrowing(){
        log.info("afterThrowing...");
    }
















}
