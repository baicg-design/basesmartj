package com.bcg.config.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @Auther: baicg
 * @Date: 2018/12/22 15:35
 * @Description:配置过滤器,需要拦截哪些url,忽略哪些url,初始化参数等
 */
@WebFilter(filterName = "druidStatFilter",//过滤器名称
        urlPatterns = "/",//需要拦截的url
        initParams = {//filter初始化信息
                //需要忽略的资源
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg," +
                        "*.bmp,*.png,*.css,*.ico,/bcg/*"),
                @WebInitParam(name = "sessionStatEnable", value = "true"),
                @WebInitParam(name = "profileEnable", value = "true")})
public class DruidStatFilter extends WebStatFilter {
}