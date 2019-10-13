package com.bcg.responsentity;

import com.bcg.entity.UserBasic;
import com.bcg.tools.Head;
import com.bcg.tools.Ret;
import lombok.Data;

/**
 * @Auther: baicg
 * @Date: 2018/11/21 22:59
 * @Description:应答实体类
 */
@Data
public class ResponseUserBasic {

    private Head head;

    private UserBasic userBasic;


}
