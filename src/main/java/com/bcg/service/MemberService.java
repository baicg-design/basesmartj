package com.bcg.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

/**
 * @Auther: baicg
 * @Date: 2018/11/14 19:55
 * @Description:
 */
@Slf4j
@Service
public class MemberService {
    //这样就会异步发送邮件
    //这个方法就相当于重新启动了一个线程单独执行会异步调用
    @Async
    public String addMemberAndEmail(){

        log.info("2----2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("3----3");
        return "addMemberAndEmail";

    }

}
