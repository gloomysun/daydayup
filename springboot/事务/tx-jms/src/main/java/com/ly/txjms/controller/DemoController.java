package com.ly.txjms.controller;

import com.ly.txjms.activemq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private Sender sender;
    @RequestMapping("/send")
    public String  send(){
        sender.send();
        return "success";
    }
}
