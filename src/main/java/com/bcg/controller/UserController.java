package com.bcg.controller;

import com.alibaba.fastjson.JSONObject;
import com.bcg.entity.User;
import com.bcg.entity.UserBasic;
import com.bcg.responsentity.ResponseUserBasic;
import com.bcg.service.UserService;
import com.bcg.tools.Ret;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: baicg
 * @Date: 2018/11/15 12:41
 * @Description:用户登录
 */
@Slf4j
@Controller
public class UserController {
    //应答对象创建
    Ret v_ret = new Ret();
    @Autowired
    private UserService userService;
    /*
    @RequestMapping("/")
    public int insertUser(String id, String name,String pass,String remark) {
        log.info("sddfsdfsfsfsd");
        return userService.insertUser(id, name, pass, remark);
    }
    */
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    //@RequestMapping(value = "/checkUser",method = RequestMethod.POST, produces="application/x-www-form-urlencoded;charset=UTF-8")

    @ResponseBody
    public Ret checkUser(@RequestBody JSONObject jsonParam){

         log.info("+++++jsonParam+++=->" + jsonParam.toJSONString());


         /*
          //组装数据
          User v_user = new User();
          v_user.setName(jsonParam.get("id").toString());
          v_user.setPass(jsonParam.get("pass").toString());
          //获取应答结果
          boolean v_flag = userService.checkUser(v_user);
          log.info("+++v_flag++++-->" + v_flag);
          if (v_flag){
              //跳转到主页面
              v_ret.setRet_code("000000");
              v_ret.setRet_msg("Success");
              v_ret.setRet_status("S");
              return v_ret;
          }else{
              v_ret.setRet_code("999999");
              v_ret.setRet_msg("用户名或密码有误");
              v_ret.setRet_status("F");
              return v_ret;
          }
        */
        return null;
    }
    //账户信息查询   支持分页
    @RequestMapping(value = "/findAll", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public List<ResponseUserBasic> findAll(@RequestBody JSONObject jsonParam){

        log.info("++++++-before---->" + jsonParam.toJSONString());
        //获取头
        JSONObject head = jsonParam.getJSONObject("head");
        //获取当前页数
        Integer v_pageIndex = Integer.parseInt(head.get("pageIndex").toString());
        //获取每页显示的条数
        Integer v_pageSize = Integer.parseInt(head.get("pageSize").toString());
        log.info("PageIndex-->" + v_pageIndex);
        log.info("pageSize-->" + v_pageSize);
        //使用PageHelp进行分页查询
        PageHelper.startPage(v_pageIndex,v_pageSize);
        List<UserBasic> list = userService.findAll();

        PageInfo<UserBasic> pageInfo = new PageInfo<UserBasic>(list);
        log.info("------>" + pageInfo.getPageSize());
        log.info("------>" + pageInfo.getFirstPage());
        log.info("------>" + pageInfo.getNextPage());
        log.info("------>" + pageInfo.getPageNum());
        log.info("------>" + pageInfo.getTotal());
        return null;
    }


}
