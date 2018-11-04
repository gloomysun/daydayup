package com.ly.order.web;


import com.ly.order.dao.OrderRepository;
import com.ly.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/all")
    public List<Order> list(){
        return orderRepository.findAll();
    }
}
