package com.ly.feignconsumer.service;

import com.ly.feignconsumer.domain.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("fallback/api/user")
public class SchedualServiceHiHystric implements CustomerService{
    @Override
    public List<Customer> list() {
        return null;
    }
}
