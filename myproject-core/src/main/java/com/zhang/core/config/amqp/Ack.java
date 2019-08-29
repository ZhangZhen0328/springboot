package com.zhang.core.config.amqp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @author zhen  2019-08-16 14:19
 * Ack
 **/
public interface Ack {

    /**
     * 自动ACK机制
     *
     * @param channel 通道
     * @param message 消息
     */
    default void basicAck(Channel channel, Message message) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
