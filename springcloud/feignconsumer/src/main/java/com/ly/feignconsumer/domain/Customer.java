package com.ly.feignconsumer.domain;

import lombok.Data;



@Data
public class Customer {

    private Long id;

    private String username;

    private String password;

    private String role;




}
