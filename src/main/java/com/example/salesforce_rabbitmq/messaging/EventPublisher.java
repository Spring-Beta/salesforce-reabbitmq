package com.example.salesforce_rabbitmq.messaging;

import com.example.salesforce_rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final AmqpTemplate amqpTemplate;

    public EventPublisher(AmqpTemplate template) {
        this.amqpTemplate = template;
    }

    public void publish(String payload) {
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                payload
        );
        System.out.println("Published event to RabbitMQ");
    }
}
