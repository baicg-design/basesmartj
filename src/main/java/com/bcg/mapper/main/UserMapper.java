package com.bcg.mapper.main;

import com.bcg.entity.User;
import com.bcg.entity.UserBasic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Auther: baicg
 * @Date: 2018/11/15 12:24
 * @Description: 数据库访问接口
 */
public interface UserMapper {
    /*
    //根据用户编号查询用户名称和密码
    @Select("SELECT * FROM USER_BASIC WHERE NAME = #{id}")
    public User findById(@Param("id") String id);
    //新增用户
    @Insert("INSERT INTO USER_BASIC VALUES(#{id},#{name},#{pass},#{remark})")
    public int insert(@Param("id") String id,@Param("name") String name,@Param("pass") String pass,@Param("remark") String remark);
    */
    public UserBasic selectUser(Map<String,String> map);

    /**
     * baicg
     * 功能：用户登录信息校验
     * @param user
     * @return
     */
    //public User checkUser(User user);

    /**
     * 用户信息查询   支持分页
     * @return   List<UserBasic>用户信息结果
     */
    //public List<UserBasic> findAll(Map<String,Integer> map);

    //public List<UserBasic> findAll();

}
