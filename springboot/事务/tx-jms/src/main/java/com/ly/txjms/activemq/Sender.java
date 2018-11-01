package com.ly.txjms.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send() {

        jmsTemplate.convertAndSend("test.queue", "生产者1辛苦生产的点对点消息成果");
    }
}
