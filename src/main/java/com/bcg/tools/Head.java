package com.bcg.tools;

import lombok.Data;

/**
 * @Auther: baicg
 * @Date: 2018/11/22 11:28
 * @Description:报文头实体层
 */
@Data
public class Head {
    //应答对象
    private Ret ret;
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

}
