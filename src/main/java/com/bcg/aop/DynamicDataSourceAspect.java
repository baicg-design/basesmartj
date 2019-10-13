package com.bcg.aop;

import com.bcg.config.DynamicDataSourceHolder;
import com.bcg.config.impl.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Auther: baicg
 * @Date: 2018/12/22 15:03
 * @Description:数据源执行切面类
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {
@Around("execution(public * com.bcg.service..*.*(..))")
public Object around(ProceedingJoinPoint pjp) throws Throwable{

    MethodSignature methodSignature = (MethodSignature)pjp.getSignature();

    Method targetSignature = methodSignature.getMethod();

    if (targetSignature.isAnnotationPresent(TargetDataSource.class)){

        String targetDataSource = targetSignature.getAnnotation(TargetDataSource.class).dataSource();

        log.info("++++++++targetDataSource++获取到的数据源是：+++-->" + targetDataSource);

        DynamicDataSourceHolder.setDataSource(targetDataSource);

    }

    Object result = pjp.proceed();

    DynamicDataSourceHolder.clearDataSource();

    return result;

}


}
