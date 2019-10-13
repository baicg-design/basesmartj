package com.bcg.service.userbasic;/**
 * Created by 11857 on 2019/9/23.
 */

import com.bcg.entity.UserBasic;
import com.bcg.exception.BusinessException;
import com.bcg.mapper.main.UserMapper;
import com.bcg.tools.BusiUtil;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils.*;

/**
 * 用户登录处理流程
 * @ClassName LoginService
 * @Description 登录服务端处理逻辑
 * @Author BAICG
 * @Date 2019-09-23 19:47
 * @Version 1.0
 */
@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    public Map<String,String> login(String userName,String passWord){

        Map<String,String> resultMap = new HashMap<String,String>();

        //根据用户名称查询密码信息
        Map<String,String> map = new HashMap<String,String>();
        map.put("userName",userName);
        UserBasic userBasic = userMapper.selectUser(map);
        //判空
        if (null == userBasic){
            throw new BusinessException("100000","用户不存在");
        }
        //生成token
        Map<String, Object> payload = new HashMap<String,Object>();
        payload.put("userName",userName);
        String token = BusiUtil.createToken(payload);
        //将生成的Token放入报文头

        resultMap.put("token",token);


        return resultMap;
    }

}
