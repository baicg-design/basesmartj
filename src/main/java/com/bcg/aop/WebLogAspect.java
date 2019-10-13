package com.bcg.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Auther: baicg
 * @Date: 2018/11/14 00:08
 * @Description: 使用AOP技术进行日志拦截
 */
@Slf4j           //lombok技术
@Aspect
@Component
public class WebLogAspect {

    //private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    //定义切入点
    @Pointcut("execution(public * com.bcg.controller.*.*(..))")
    public void webLog(){
    }
    //使用AOP前置通知拦截参数信息
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        //收到请求后打印请求内容--记录请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印
        log.info("URL:" + request.getRequestURI().toString());
        log.info("HTTP_METHOD:" + request.getMethod());
        log.info("IP:" + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String name = (String)enu.nextElement();
            log.info("Name--Value->" + name + ":" + request.getParameter(name));
        }
    }
    //后置通知打印参数信息
    @AfterReturning(returning = "obj" ,pointcut = "webLog()")
    public void doAfterReturning(Object obj) throws Throwable{
        //打印请求结果
        log.info("ResponseValue:" + obj);
    }
}
