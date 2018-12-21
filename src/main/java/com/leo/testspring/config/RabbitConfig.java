package com.leo.testspring.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue dateQueue() {
        return new Queue("date");
    }

    @Bean
    public Queue objQueue() {
        return new Queue("object");
    }
}
