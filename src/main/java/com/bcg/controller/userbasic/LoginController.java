package com.bcg.controller.userbasic;


import com.alibaba.fastjson.JSONObject;
import com.bcg.ResponseCode;
import com.bcg.entity.ResponseResult;
import com.bcg.exception.BusinessException;
import com.bcg.service.userbasic.LoginService;
import com.bcg.tools.BusiUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by baicg on 2018/11/13.
 * lombok技术使开发更敏捷    底层使用反射技术获取到加注解的类然后加对应的代码生成class文件
 * 生产环境不需要安装lombok，因为lombok是在编译的时候修改字节码文件
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    //初始用户登录
    @RequestMapping(value = "/loginUser")
    @ResponseBody
    //public String loginController(@RequestBody Map map){
    public ResponseResult loginController(HttpServletRequest request){

        //数据转换
        Map reqMap = BusiUtil.getParameterMap(request);
        //用户名称
        String userName = reqMap.get("username").toString();
        //密码
        String password = reqMap.get("password").toString();

        //非空判断
        if(StringUtils.isEmpty(userName)){
            throw new BusinessException("200001","用户名称位必输项");
        }

        if(StringUtils.isEmpty(password)){
            throw new BusinessException("200002","密码为必输项");
        }

        Map<String,String> mapResult  = loginService.login(userName,password);

        log.info("+++token++++++-->" + mapResult.get("token"));

        return new ResponseResult("000000","Success",mapResult);
    }
}
