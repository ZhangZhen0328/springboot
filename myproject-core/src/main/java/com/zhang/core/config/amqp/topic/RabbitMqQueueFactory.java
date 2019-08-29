package com.zhang.core.config.amqp.topic;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhen  2019-08-16 14:22
 * 队列工厂 负责向spring 注册队列
 **/
@Configuration
public class RabbitMqQueueFactory implements RabbitMqQueue{

    @Bean(MESSAGE)
    Queue message(){ return new Queue(MESSAGE); }
}
