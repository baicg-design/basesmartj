package com.bcg.controller;

import com.bcg.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: baicg
 * @Date: 2018/11/14 19:52
 * @Description: Spring boot异步技术
 * 1、在启动类上添加注释@EnableAsync
 * 2、在异步方法上面使用@Async后该方法就会异步调用
 */
@Slf4j
@RestController
public class MemberController {
    //自动注入
    @Autowired
    MemberService  memberService;

    @RequestMapping("/memberController")
    public String addMemberAndEmail(){

        log.info("1-----1");
        //调用的方法里面使用了异步调用的注解@Async，此处应该拿不到结果的
        String str = memberService.addMemberAndEmail();

        log.info("4----4");

        return "result:" + str;

    }

}
