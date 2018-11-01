package com.ly.txjta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class JmsConfig {

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }

    // 这个用于设置 @JmsListener使用的containerFactory
    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory
//            ,JmsTransactionManager transactionManager
    ){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory ();
//        factory.setSessionTransacted(true);
        factory.setConnectionFactory(connectionFactory);
//        factory.setTransactionManager(transactionManager);
//        factory.setTransactionManager(jtaTransactionManager);
        factory.setReceiveTimeout(1000L); //重连间隔时间
        return factory;
    }

//    @Bean
//    public JmsTransactionManager transactionManager(ConnectionFactory connectionFactory) {
//        return new JmsTransactionManager(connectionFactory);
//    }

}
