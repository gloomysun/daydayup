package com.ly.mqconfirm.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://www.cnblogs.com/milicool/p/9662447.html
@Configuration
public class RabbitmqConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        // 消息返回, yml需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText,
                                        String exchange, String routingKey) {
                System.out.println("确认后回调return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode + ",replyText:"
                        + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
            }
        });
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    System.out.println("消息确认成功cause" + cause);
                } else {
                    //处理丢失的消息
                    System.out.println("消息确认失败" + correlationData.getId() + "#cause" + cause);
                }
            }
        });
        return rabbitTemplate;
    }
}
