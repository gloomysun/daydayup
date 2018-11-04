package com.ly.activemqbase.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    /*
    每5秒执行一次
     */
    @Scheduled(fixedRate = 5000, initialDelay = 3000)
    public void send() {
        //发送队列消息
        jmsTemplate.convertAndSend(this.queue, "点对点生产者消息");
        //发送订阅消息
        jmsTemplate.convertAndSend(this.topic, "发布订阅消息");
    }
}
