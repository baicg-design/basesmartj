package com.bcg.interceptor;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Auther: baicg
 * @Date: 2018/11/29 23:16
 * @Description: MyBatis+pagehelper分页配置类
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public PageHelper pageHelper(){
        System.out.print("pageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");

        pageHelper.setProperties(properties);

        return pageHelper;
    }

}
