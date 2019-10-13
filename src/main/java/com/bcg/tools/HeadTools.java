package com.bcg.tools;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: baicg
 * @Date: 2018/11/22 11:36
 * @Description: 系统头封装
 */
@Slf4j
public class HeadTools {

    //前端流水号
    private String seqNo;
    //前端类型
    private String sourceType;
    //请求日期
    private String tranDate;
    //请求时间戳
    private String tranTimeStamp;
    //预留字段1
    private String field1;
    //预留字段2
    private String field2;

    //生成系统头（默认为空）
    public static Head getHead(){
        return new Head();
    }

    //生成系统头（有初始化）
    public static Head getHead(JSONObject jsonObject){

        Head head = new Head();
        try {
            head.setSeqNo(jsonObject.get("seqNo").toString());
        }catch (Exception e){
            log.info("获取（seqNo）异常");
        }

        try {
            head.setSourceType(jsonObject.get("sourceType").toString());
        }catch (Exception e){
            log.info("获取（sourceType）异常");
        }

        try {
            head.setTranDate(jsonObject.get("tranDate").toString());
        }catch (Exception e){
            log.info("获取（tranDate）异常");
        }

        try {
            head.setTranTimeStamp(jsonObject.get("tranTimeStamp").toString());
        }catch (Exception e){
            log.info("获取（tranTimeStamp）异常");
        }

        return head;
    }

}
