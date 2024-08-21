package com.ray.aop;

import com.alibaba.fastjson.JSONObject;
import com.ray.mapper.OperateLogMapper;
import com.ray.pojo.OperateLog;
import com.ray.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.ray.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 操作人ID - 当前登录员工ID
        // 获取请求头中的jwt令牌，解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作类方法名
        String methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParam = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        System.out.println(111111111);

        // 调用原始目标方法运行
        Object result = joinPoint.proceed();

        System.out.println(222222222);

        long end = System.currentTimeMillis();

        // 方法返回值
        String returnValue = JSONObject.toJSONString(result);

        // 操作耗时
        Long costTime = end - begin;


        // 记录操作日志 (全参构造)
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParam, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志: {}",operateLog);
        return result;
    }
}
