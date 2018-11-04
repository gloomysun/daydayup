package com.ly.txjms.controller;

import com.ly.txjms.activemq.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Receiver receiver;

    /**
     * 通过@JmsListener监听消息并发送出去
     */
    @RequestMapping("/send")
    public void send(String msg) {
        jmsTemplate.convertAndSend("test.queue.first", msg);
    }

    /**
     * 直接发送
     */
    @RequestMapping("/direct")
    public void direct(String msg) {
        receiver.receive(msg);
    }
}
