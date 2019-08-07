package com.itheima.core.dao;

import com.itheima.core.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    //通过账号密码查询账户
    public User  findUser(@Param("usercode")String usercode,@Param("password")String passwprd);
    //@param（“usercode”）给usercode命名为usercode   @Param的作用就是给参数命名 命名后#{usercode 就可以获取usercode的值}
}
