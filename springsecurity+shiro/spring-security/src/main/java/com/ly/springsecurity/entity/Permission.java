package com.ly.springsecurity.entity;

import lombok.Data;

@Data
public class Permission {
    private long id;
    private String name;  //权限名称
    private String description;  //权限描述
    private String url; //授权链接
    private long pid;  //父节点


}
