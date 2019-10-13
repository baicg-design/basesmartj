package com.bcg.exception;/**
 * Created by 11857 on 2019/9/29.
 */

import lombok.Getter;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author 11857
 * @Date 2019-0929 21:29
 * @Version 1.0
 */
@Getter
public class BusinessException extends RuntimeException{
    //错误码
    private String errorCode;
    //错误原因
    private String errorMsg;

    public BusinessException(){
        this.errorCode = "999999";
        this.errorMsg = "系统异常";
    }

    //构造方法
    public BusinessException(String errorCode){
        this.errorCode = errorCode;
        this.errorMsg = "";
    }
    //构造方法
    public BusinessException(String errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
