package com.ly.activemqbase.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String msg) {
        System.out.println("消费者收到点对点：" + msg);
    }

    @JmsListener(destination = "sample.topic")
    public void receiveSub1(String msg) {
        System.out.println("消费者2收到：" + msg);
    }
    @JmsListener(destination = "sample.topic")
    public void receiveSub2(String msg) {
        System.out.println("消费者3收到：" + msg);
    }
}
