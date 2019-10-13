package com.bcg.tools;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: baicg
 * @Date: 2018/11/16 23:11
 * @Description: 应答码实体类
 */
@Slf4j
@Data
public class Ret {

    //应答码：000000-成功
    private String retCode;
    //应答消息：Success-成功
    private String retMsg;

    public Ret(){
    }

    public Ret(String retCode){
        this.retCode = retCode;
    }

    public Ret(String retCode,String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
}
