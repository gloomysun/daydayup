package com.ly.txjta.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {


    @JmsListener(destination = "test.queue")
    public void receive(String msg) {
        int i =1/0;

    }
}
