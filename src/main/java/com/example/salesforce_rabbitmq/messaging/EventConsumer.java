package com.example.salesforce_rabbitmq.messaging;

import com.example.salesforce_rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleMessage(String message) {
        System.out.println("Consumed message from RabbitMQ: " + message);
        // process and maybe call another API, or write to DB
    }
}
