package com.sun.health.rabbitmqhello.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.rabbitmq.client.AMQP.*;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue();
    }

}
