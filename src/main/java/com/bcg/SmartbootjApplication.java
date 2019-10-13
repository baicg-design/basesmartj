package com.bcg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 *1、mybatis启动方式可以在mapper层不需要添加注解，但是一定要在启动类上加@MapperScan(basePackages = {"com.bcg.mapper"})
 * 2、mybatis在mapper层添加@Mapper注解，不需要在启动类上添加@MapperScan注解。
 */
@EnableAsync           //异步注释
@SpringBootApplication     //启动注解
@EnableAutoConfiguration //自动加载配置信息
@MapperScan(basePackages = {"com.bcg.mapper"})          //扫描mapper层注解
//@EnableScheduling       //启动定时任务配置类
public class SmartbootjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartbootjApplication.class, args);
	}
}
