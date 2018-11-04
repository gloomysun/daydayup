package com.ly.txjta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public void send(){
        jmsTemplate.convertAndSend("test.queue","你好啊");
    }
}
