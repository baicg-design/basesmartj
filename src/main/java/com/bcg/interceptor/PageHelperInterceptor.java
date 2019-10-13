package com.bcg.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import java.sql.Connection;

import java.util.Properties;

/**
 * @Auther: baicg
 * @Date: 2018/11/29 22:56
 * @Description:MyBatis分页拦截器
 * 在编译SQL的时候拦截
 */
@Slf4j
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class}) })
public class PageHelperInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        log.info("已经拦截住了");

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
