package com.ly.springsecurity.entity;

import lombok.Data;

import java.util.Set;

@Data
public class SysUser {
    private long id;
    private String username;
    private String password;

    private Set<Role> roleSet;
}
