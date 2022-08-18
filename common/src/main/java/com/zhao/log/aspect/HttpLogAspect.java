package com.zhao.log.aspect;

import com.zhao.log.HttpLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
 *  提供两种日志方式
 *  1. 添加注解的自定义
 *  2. 默认对 Controller包下的方法进行日志记录
 */

@Aspect
@Component
@Slf4j
public class HttpLogAspect {


    @Pointcut("@annotation(com.zhao.log.annotation.HttpLog)")
    public void pta() {};

    @Pointcut("execution(public * com.zhao.module.system.controller..*.*(..))")
    public void httpLog() {};

    @Before("httpLog()")
    public void doBefore(JoinPoint joinPoint) {
        String executionExpression = joinPoint.toString();
        HttpLog httpLog = new HttpLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        httpLog.setDescription(executionExpression);
        httpLog.setRemoteAddr(request.getRemoteAddr());
        httpLog.setQueryString(request.getQueryString());
        httpLog.setRequestURI(request.getRequestURI());
        httpLog.setRequestMethod(request.getMethod());
        log.info(httpLog.toString());
    };



    @AfterReturning("httpLog()")
    public void doAfterReturn(){
    }


    @Around("pta()")
    public Object dolog(ProceedingJoinPoint joinPoint) {
        String executionExpression = joinPoint.toString();
        HttpLog httpLog = new HttpLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        httpLog.setDescription(executionExpression);
        httpLog.setRemoteAddr(request.getRemoteAddr());
        httpLog.setQueryString(request.getQueryString());
        httpLog.setRequestURI(request.getRequestURI());
        httpLog.setRequestMethod(request.getMethod());
        log.info(httpLog.toString());
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return proceed;
    }
}
