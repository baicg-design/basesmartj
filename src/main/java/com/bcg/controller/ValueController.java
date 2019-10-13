package com.bcg.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: baicg
 * @Date: 2018/11/14 20:20
 * @Description:获取自定义参数
 * 1、在application.properties中定义参数
 * 2、是注释 @Value("${remote_ip}")获取就完了
 */
@Slf4j
@RestController
public class ValueController {

    @Value("${remote_ip}")          //获取application.properties中定义的参数 remote_ip
    private String remote_ip;

    @Value("${server-port}")
    private String server_port;

    //返回在application.properties中定义的参数值
    @RequestMapping("/getName")
    public String getName(){
        return remote_ip;
    }
    //返回在application-dev.properties中定义的参数值
    @RequestMapping("/getPort")
    public String getPort(){
        return server_port;
    }
}
