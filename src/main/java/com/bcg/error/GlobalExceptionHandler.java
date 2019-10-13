package com.bcg.error;

import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: baicg
 * @Date: 2018/11/13 21:57
 * @Description:全局捕获异常处理逻辑
 * 1、捕获JSON格式
 * 2、捕获异常页面
 */
//@ControllerAdvice  表示横切点
//@ControllerAdvice(basePackages = "com.bcg.controller")
public class GlobalExceptionHandler {
    //@ResponseBody  表示返回的是JSON格式
    //modeAndview   表示返回的是页面
    //@ExceptionHandler(RuntimeException.class) 表示抛出运行时错误时拦截
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public Map<String,Object> errorResult(){
//
//        Map<String,Object> errorMap = new HashMap<String,Object>();
//
//        errorMap.put("errorCode","999999");
//        errorMap.put("errorMsg","系统异常，请联系管理员！");
//        //真正开发时需要处理，将异常情况插入数据库！
//        return errorMap;
//
//
//    }

}
