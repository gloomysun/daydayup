package com.ly.txjms.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;

@Component
public class Receiver {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "test.queue.first")
    public void receive(String msg){
        System.out.println("收到的消息：" + msg);
        jmsTemplate.setSessionTransacted(true);
        jmsTemplate.convertAndSend("test.queue.reply", "reply:" + msg);
        //发送消息包含error时抛出异常
        if(msg.contains("error")){
            throw new RuntimeException("data error!!!!!!!!!!!!!!!!!1");
        }
    }
}
