package com.zhang.core.config.amqp;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhen  2019-08-16 14:19
 * MQ 交换机中心
 * 负责管理交换机
 */
@Configuration
public class RabbitMqExchange {

    /**
     * 交换机配置中心
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ExchangeCenter {

        // A 号交换机
        public static final String TOPIC_A = "csxy_topic_a";

        /**
         * 死信生产
         */
        public static final String DEAD_LETTER_EXCHANGE_PROD = "dead_letter_exchange_prod";
        /**
         * 死信消费
         */
        public static final String DEAD_LETTER_EXCHANGE_CONSUME = "dead_letter_exchange_consume";


    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(ExchangeCenter.TOPIC_A);
    }


    @Bean
    DirectExchange directExchangeProd() {
        return new DirectExchange(ExchangeCenter.DEAD_LETTER_EXCHANGE_PROD);
    }

    @Bean
    DirectExchange directExchangeConsume() {
        return new DirectExchange(ExchangeCenter.DEAD_LETTER_EXCHANGE_CONSUME);
    }


}
