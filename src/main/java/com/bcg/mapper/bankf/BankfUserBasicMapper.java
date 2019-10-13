package com.bcg.mapper.bankf;

import com.bcg.entity.UserBasic;

public interface BankfUserBasicMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserBasic record);

    int insertSelective(UserBasic record);

    UserBasic selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserBasic record);

    int updateByPrimaryKey(UserBasic record);
}