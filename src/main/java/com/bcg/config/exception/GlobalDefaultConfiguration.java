package com.bcg.config.exception;/**
 * Created by 11857 on 2019/9/29.
 */

import com.bcg.exception.GlobalDefaultExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName GlobalDefaultConfiguration
 * @Description TODO
 * @Author 11857
 * @Date 2019-09-29 21:42
 * @Version 1.0
 */
//@Configuration
//@EnableConfigurationProperties(GlobalDefaultConfiguration.class)
//@PropertySource(value = "classpath:exception.properties", encoding="UTF-8")
public class GlobalDefaultConfiguration {

    //@Bean
    public GlobalDefaultExceptionHandler globalDefaultExceptionHandler(){

    return new GlobalDefaultExceptionHandler();
    }

}
