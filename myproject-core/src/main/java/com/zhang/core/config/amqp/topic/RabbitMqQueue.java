package com.zhang.core.config.amqp.topic;

import com.zhang.core.config.amqp.Ack;

/**
 * @author zhen  2019-08-16 14:20
 * <p>
 * RabbitMqQueue
 **/
public interface RabbitMqQueue extends Ack {


    /**
     * 消息队列
     */
    String MESSAGE = "message";

}
