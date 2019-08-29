package com.zhang.core.config.amqp.topic;

import com.zhang.core.config.amqp.Ack;

/**
 * @author zhen  2019-08-16 14:35
 * 死信队列
 * DeadLetterQueue
 **/
public interface DeadLetterQueue  extends Ack {

    String DEAD_LETTER_PROD_CLOSE_ORDER = "deadLetterProdCloseOrder";
    String DEAD_LETTER_CONSUME_CLOSE_ORDER = "deadLetterConsumeCloseOrder";
}
