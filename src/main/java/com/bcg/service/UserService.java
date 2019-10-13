package com.bcg.service;

import com.bcg.entity.User;
import com.bcg.entity.UserBasic;
import com.bcg.mapper.main.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: baicg
 * @Date: 2018/11/15 12:34
 * @Description:
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    //使用SpringBoot事物注解，事物具有一致性，要么同时成功要么同时失败
    //使用事物的时候不要加try-catch
    //@Transactional   事物注解
    public int insertUser(String id, String name,String pass,String remark){
        log.info("34546456546775");
        //int result = userMapper.insert(id, name, pass, remark);
        //int b = 1/Integer.parseInt(pass);                               //插入失败以后会自动回滚//测试事物是否生效
        //log.info("#####result:{}####" + result);
        return 1;
    }

    public List<User> selectUser(){

        return null;//userMapper.selectUser();

    }

    public boolean checkUser(User user){
        //获取查询结果
        //User v_user = userMapper.checkUser(user);
        //log.info("v_user--->" + v_user);
        //返回验证成功失败标志
        if (null == null){
            return false;
        }else{
            return true;
        }
    }

/*    public List<UserBasic> findAll(Map<String,Integer> map){

        List<UserBasic> list = userMapper.findAll(map);

        log.info("+++++list+-->" + list);
        for(int i = 0 ; i < list.size();i ++){

            log.info( "list+++"+ i + "++++-->" + list.get(i).toString());

        }
        return list;

    }*/

    public List<UserBasic> findAll(){

        /*List<UserBasic> list = userMapper.findAll();

        log.info("+++++list+-->" + list);
        for(int i = 0 ; i < list.size();i ++){

            log.info( "list+++"+ i + "++++-->" + list.get(i).toString());

        }*/
        return null;

    }

}
