package com.ly.txjta.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name", unique = true)
    private String username;

    private String password;


    public Customer(String username,String password){
        this.username=username;
        this.password=password;
    }
}
