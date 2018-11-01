package com.ly.txjms.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class Receiver {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "test.queue")
    public void receive(TextMessage msg) throws JMSException {
        System.out.println("收到的消息：" + msg.getText());
        jmsTemplate.convertAndSend("test.queue2","生产者2辛苦生产的点对点消息成果");
        System.out.println("生产者2：辛苦生产的点对点消息成果");
        throw new RuntimeException("receive error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
