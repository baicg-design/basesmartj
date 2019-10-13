package com.bcg.config.impl;

import java.lang.annotation.*;

/**
 * @Auther: baicg
 * @Date: 2018/12/22 15:12
 * @Description:自定义注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  TargetDataSource {

    String dataSource() default "";

}

