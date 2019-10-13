package com.bcg.entity;

import lombok.Data;
import java.io.Serializable;
/**
 * @ClassName ResponseResult
 * @Description 统一饭回结果封装
 * @Author 11857
 * @Date 2019-09-24 20:08
 * @Version 1.0
 */
@Data
public class ResponseResult implements Serializable {

    /**
     * 返回状态码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
    //空构造方法
    public ResponseResult(){

        this.code = "000000";

        this.msg = "";

        this.data = null;

    }

    //只有饭回状态和饭回码的构造方法
    public ResponseResult(String code,String msg){

        this.code = code;

        this.msg = msg;

        this.data = null;

    }
    //三个参数都有的构造方法
    public ResponseResult(String code,String msg,Object data){

        this.code = code;

        this.msg = msg;

        this.data = data;

    }

    //只有状态码和数据的构造方法
    public ResponseResult(String code, Object data){

        this.code = code;

        this.data = data;

        this.msg = "";
    }
}
