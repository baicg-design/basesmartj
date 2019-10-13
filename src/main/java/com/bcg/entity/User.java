package com.bcg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: baicg
 * @Date: 2018/11/14 19:04
 * @Description:lombok底层使用字节码技术ASM 修改字节码文件 生成get和set方法
 * lombok技术简化代码的开发它可以使用注解的方式使开发更敏捷
 */
@Slf4j
@Getter
@Setter
public class User {

    private String id;

    private String name;

    private String pass;

    @Override
    public String toString() {
        return "id:" + id  + " name:" + name;
    }
    /*
    public static void main(String[] args){

    User user = new User();

        user.setId("1008282");
        user.setName("baicg");

        log.info(user.toString());

}
        */
}
