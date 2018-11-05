package com.ly.user.web;

import com.ly.user.dao.CustomerRepository;
import com.ly.user.domain.Customer;
import com.ly.user.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/all")
//    @HystrixCommand
    public List<Customer> list(){
        return customerRepository.findAll();
    }
}
