package com.ly.springsecurity.entity;

import lombok.Data;

@Data
public class SysUser {
    private long id;
    private String username;
    private String password;
}
