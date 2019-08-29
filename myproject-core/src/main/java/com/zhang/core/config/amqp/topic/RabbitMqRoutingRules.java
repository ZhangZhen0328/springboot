package com.zhang.core.config.amqp.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhen  2019-08-16 14:24
 * 交换机路由规则
 * 负责向spring 注册路由规则
 * RabbitMqRoutingRules
 **/
@Configuration
public class RabbitMqRoutingRules implements RabbitMqQueue{

    @Bean
    Binding bindingSms(Queue message, TopicExchange topicExchange) {
        return BindingBuilder.bind(message).to(topicExchange).with(MESSAGE);
    }
}
