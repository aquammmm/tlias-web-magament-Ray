package com.ray.filter;

import com.alibaba.fastjson.JSONObject;
import com.ray.pojo.Result;
import com.ray.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j      // 生成日志对象
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 1. 获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}", url);

        // 2. 判断请求的url是否包含login
        if (url.contains("login")) {
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest, servletResponse);   //; 放行请求
            return;     // 结束当前方法的执行
        }

        // 3. 获取请求头的令牌(token)
        String token = req.getHeader("token");
        log.info("从请求头中获取的令牌：{}", token);

        // 4. 判断令牌是否存在，如果不存在，返回错误（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("Token不存在");

            Result responseResult = Result.error("Not_Login");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的 用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            resp.setContentType("application/json;charset=utf-8");

            // 响应
            resp.getWriter().write(json);
            return;
        }


        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌解析失败!");
            Result responseResult = Result.error("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            resp.setContentType("application/json;charset=utf- 8");

            //响应
            resp.getWriter().write(json);
            return;
        }


        //6.放行
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
