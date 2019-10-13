package com.bcg.config.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @Auther: baicg
 * @Date: 2018/12/22 15:37
 * @Description:servlet配置
 */
//
@WebServlet(urlPatterns = "/bcg/*",//通过哪个url访问
        initParams = {
                @WebInitParam(name = "loginUsername", value = "admin"),//用户名
                @WebInitParam(name = "loginPassword", value = "123456"), //密码
                @WebInitParam(name = "resetEnable", value = "true"),//是否可以重置
                // @WebInitParam(name = "allow",value = "127.0.0.1"),//白名单
                // @WebInitParam(name = "deny",value = "192.168.1.1")//黑名单
        })
public class DruidStatViewServlet extends StatViewServlet {
}
