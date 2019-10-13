package com.bcg.service;

import com.bcg.config.impl.TargetDataSource;
import com.bcg.entity.UserBasic;
import com.bcg.mapper.bankf.BankfUserBasicMapper;
import com.bcg.mapper.banks.BanksUserBasicMapper;
import com.bcg.mapper.main.UserBasicMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: baicg
 * @Date: 2018/11/21 21:25
 * @Description:
 */
@Slf4j
@Service
//@TargetDataSource(dataSource = "mainDataSource")
public class UserBasicService {
    @Autowired
    private UserBasicMapper userBasicMapper;

    //插入全部字段
    public int insert(UserBasic userBasic){

        return userBasicMapper.insert(userBasic);
    }
    //分页模糊查询
    public List<UserBasic> selectByIdOrName(UserBasic userBasic){
        return userBasicMapper.selectByIdOrName(userBasic);
    }
    //插入部分字段
    public int insertSelect(UserBasic userBasic){
        return userBasicMapper.insertSelective(userBasic);
    }

    //主键查询
    public UserBasic selectByPrimaryKey(String userId){

        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(userId);

        return userBasic;

    }

    //其它字段更新
    public int updateByPrimaryKeySelective(UserBasic record){
        return userBasicMapper.updateByPrimaryKeySelective(record);
    }

    //根据主键更新
    public int updateByPrimaryKey(UserBasic record){
        return userBasicMapper.updateByPrimaryKey(record);
    }

    //根据主键删除
    public int deleteByPrimaryKey(String userId){
        return userBasicMapper.deleteByPrimaryKey(userId);
    }
}
