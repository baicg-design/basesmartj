package com.bcg.service;

import com.bcg.tools.BusiUtil;
import com.bcg.tools.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Auther: baicg
 * @Date: 2019/1/7 19:23
 * @Description:定时任务类测试
 */
@Slf4j
@Component
public class SheduleTaskService {
    private int count=0;
    @Scheduled(cron = "*/5 * * * * ?")
    public void bonusPeriodAutoIncrease(){

      log.info("[" + Thread.currentThread().getName() + "]" + "this is scheduler task runing  "+(count++));

    }

}
