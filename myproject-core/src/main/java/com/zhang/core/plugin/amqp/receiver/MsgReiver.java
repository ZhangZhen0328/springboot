package com.zhang.core.plugin.amqp.receiver;

import com.rabbitmq.client.Channel;
import com.zhang.core.config.amqp.topic.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhen  2019-08-16 15:33
 * <p>
 * MsgReiver
 **/
@Slf4j
@Component
public class MsgReiver implements RabbitMqQueue {

    @RabbitListener(queues = MESSAGE)
    public void receiver(Channel channel, Message message){
        basicAck(channel,message);
        log.info("测试消费成功");
    }
}
