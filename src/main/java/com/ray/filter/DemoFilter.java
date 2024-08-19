package com.ray.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    // 初始化方法，只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("开始初始化了");
    }

    // 拦截到请求之后调用，可以调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("放行");

    }

    // 在服务器关闭之后自动调用的销毁方法
    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("结束了");
    }
}
