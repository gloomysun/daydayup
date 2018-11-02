package com.ly.txjms.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String msg) {
        jmsTemplate.convertAndSend("test.queue.first", msg);
    }
}
