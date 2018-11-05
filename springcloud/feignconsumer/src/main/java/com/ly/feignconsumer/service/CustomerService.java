package com.ly.feignconsumer.service;

import com.ly.feignconsumer.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "service-zuul",fallback = SchedualServiceHiHystric.class)
@RequestMapping("/user/api/user")
//@FeignClient(value = "userservice",fallback = SchedualServiceHiHystric.class)
//@RequestMapping("/api/user")
public interface CustomerService {
    @RequestMapping("/all")
    List<Customer> list();
}
