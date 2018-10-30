package com.ly.springsecurity.dao;

import com.ly.springsecurity.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface  PermissionDao {

    @Select("select * from sys_permission")
    List<Permission> findAll();

    @Select("select p.*\n" +
            "        from Sys_User u\n" +
            "        LEFT JOIN sys_role_user sru on u.id= sru.Sys_User_id\n" +
            "        LEFT JOIN Sys_Role r on sru.Sys_Role_id=r.id\n" +
            "        LEFT JOIN Sys_permission_role spr on spr.role_id=r.id\n" +
            "        LEFT JOIN Sys_permission p on p.id =spr.permission_id\n" +
            "        where u.id=#{userId} ")
    List<Permission> findByAdminUserId(long userId);
}
