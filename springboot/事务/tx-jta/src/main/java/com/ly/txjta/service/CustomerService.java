package com.ly.txjta.service;

import com.ly.txjta.entity.Customer;
import com.ly.txjta.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional
    public void insertList(List<Customer> list) {
//        jmsTemplate.setSessionTransacted(true);
//        jmsTemplate.convertAndSend("jta.queue","haha");
        for (Customer customer : list) {
            customerRepository.save(customer);
        }
    }

    @Transactional
    @JmsListener(destination = "test.queue")
    public void listen() {
        List<Customer> list = new ArrayList();
        list.add(new Customer("zhuangsan", "123456"));
        list.add(new Customer("lisi", "123456"));
        list.add(new Customer("haha", "123456"));
        for (Customer customer : list) {
            customerRepository.save(customer);
        }
        jmsTemplate.convertAndSend("jta.queue","haha");
        System.out.println(1/0);
    }

    @Transactional
    public void send() {
        System.out.println(jmsTemplate.isSessionTransacted());
        jmsTemplate.convertAndSend("jta.queue","haha");
        List<Customer> list = new ArrayList();
        list.add(new Customer("zhangsan", "123456"));
        list.add(new Customer("lisi", "123456"));
        list.add(new Customer("zhaosi", "123456"));
        for (Customer customer : list) {
            customerRepository.save(customer);
        }
        System.out.println(1/0);
    }
}
