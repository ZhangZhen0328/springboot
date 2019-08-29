package com.zhang.core.plugin.amqp;

import com.zhang.core.config.amqp.RabbitMqExchange;
import com.zhang.core.config.amqp.topic.DeadLetterQueue;
import com.zhang.core.config.amqp.topic.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhen  2019-08-16 14:37
 * <p>
 * RabbitMqSender
 **/
@Slf4j
@Component("rabbitMqSender")
public class RabbitMqSender implements RabbitMqQueue, DeadLetterQueue {

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * 测试发送消息 TODO
     */
    public void msg(){
        String message = "测试";
        send(MESSAGE,message);
    }

    private void send(String routingKey, Object message) {
        amqpTemplate.convertAndSend(RabbitMqExchange.ExchangeCenter.TOPIC_A, routingKey, message);
    }

}
