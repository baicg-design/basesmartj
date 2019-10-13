package com.bcg.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Auther: baicg
 * @Date: 2019/1/7 20:16
 * @Description:定时任务线程池
 */
@Configuration
//@EnableScheduling
public class SchedulerConfig {
/*
    //初始化线程池
    private int POOLSIZE = 10;

    @Bean
    public TaskScheduler taskScheduler() {


        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //线程池大小
        scheduler.setPoolSize(POOLSIZE);
        //线程名字前缀
        scheduler.setThreadNamePrefix("Spring-Task-Thread-");
        return scheduler;
    }
*/
}
