package com.ly.txjms.config;

import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJcaListenerContainerFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class JmsConfig {

    //这个用于设置 @JmsListener使用的containerFactory
    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory
                                                                 ){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory ();
        // factory.setSessionTransacted(true);
        factory.setConnectionFactory(connectionFactory);
        factory.setReceiveTimeout(10000L); //重连间隔时间,设置10s防止日志打印较快
//        factory.setTransactionManager(transactionManager);
        return factory;
    }
//    @Bean
//    public JmsListenerContainerFactory<?> msgFactory(ConnectionFactory connectionFactory,
//                                                     DefaultJmsListenerContainerFactoryConfigurer configurer,
//                                                     PlatformTransactionManager transactionManager) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setTransactionManager(transactionManager);
//        factory.setReceiveTimeout(10000L);
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }


//    @Bean
//    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(connectionFactory);
//        // jmsTemplate.setSessionTransacted(true);
//        return jmsTemplate;
//    }
//
//    /**
//     * JmsTransactionManager事务管理
//     */
//    @Bean
//    public PlatformTransactionManager transactionManager(ConnectionFactory connectionFactory) {
//        return new JmsTransactionManager(connectionFactory);
//    }
}
