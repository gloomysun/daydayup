package com.ly.txjta.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfig {

//    @Bean
//    public DefaultJmsListenerContainerFactory listenerContainerFactory(
//            ConnectionFactory connectionFactory,
//            DefaultJmsListenerContainerFactoryConfigurer configurer,
//            JtaTransactionManager transactionManager) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setReceiveTimeout(20000L);
//        factory.setTransactionManager(transactionManager);
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;
    }
}
