package com.ly.springsecurity.dao;

import com.ly.springsecurity.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionDao {

    @Select("select * from sys_permission")
    List<Permission> findAll();

    @Select("select p.*\n" +
            "        from sys_user u\n" +
            "        LEFT JOIN sys_role_user sru on u.id= sru.sys_user_id\n" +
            "        LEFT JOIN sys_role r on sru.sys_role_id=r.id\n" +
            "        LEFT JOIN sys_permission_role spr on spr.role_id=r.id\n" +
            "        LEFT JOIN sys_permission p on p.id =spr.permission_id\n" +
            "        where u.id=#{userId} ")
    List<Permission> findByAdminUserId(long userId);
}
