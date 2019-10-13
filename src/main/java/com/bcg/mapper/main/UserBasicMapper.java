package com.bcg.mapper.main;

import com.bcg.entity.UserBasic;

import java.util.List;

public interface UserBasicMapper {

    int deleteByPrimaryKey(String userId);

    int insert(UserBasic record);

    int insertSelective(UserBasic record);

    UserBasic selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserBasic record);

    int updateByPrimaryKey(UserBasic record);

    public List<UserBasic> selectByIdOrName(UserBasic userBasic);
}