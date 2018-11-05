package com.ly.feignconsumer.web;


import com.ly.feignconsumer.domain.Customer;
import com.ly.feignconsumer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/user/list")
    public List<Customer> list(){
        return customerService.list();
    }
}
