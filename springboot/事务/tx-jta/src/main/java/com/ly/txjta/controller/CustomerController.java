package com.ly.txjta.controller;

import com.ly.txjta.entity.Customer;
import com.ly.txjta.service.CustomerService;
import com.ly.txjta.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SendService sendService;

    @RequestMapping("/insertList")
    public void insertList() {
        List<Customer> list = new ArrayList();
        list.add(new Customer("zhuangsan", "123456"));
        list.add(new Customer("lisi", "123456"));
        list.add(new Customer("zhuangsan", "123456"));
        customerService.insertList(list);
    }


    @RequestMapping("/listen")
    public void listen() {
        sendService.send();
    }

    @RequestMapping("/send")
    public void send() {
        customerService.send();
    }
}
