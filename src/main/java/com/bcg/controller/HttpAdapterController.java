package com.bcg.controller;

import com.bcg.tools.HttpAdapterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: baicg
 * @Date: 2018/11/23 09:50
 * @Description:
 */
@Controller
public class HttpAdapterController {

    @Autowired
    HttpAdapterEntity httpAdapterEntity;
    @RequestMapping("/httpAdapter")
    @ResponseBody
    public String httpAdapter(){
        return "url:" + httpAdapterEntity.getUrl();
    }

}
