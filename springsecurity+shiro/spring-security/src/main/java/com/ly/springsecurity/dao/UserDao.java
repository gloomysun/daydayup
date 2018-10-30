package com.ly.springsecurity.dao;

import com.ly.springsecurity.entity.SysUser;
import org.apache.ibatis.annotations.*;

public interface UserDao {
    @Select("select * from sys_user where username=#{username}")
    SysUser findByUsername(String username);

}
