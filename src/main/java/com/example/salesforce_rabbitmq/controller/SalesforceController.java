package com.example.salesforce_rabbitmq.controller;

import com.example.salesforce_rabbitmq.messaging.EventPublisher;
import com.example.salesforce_rabbitmq.service.SalesforceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/salesforce")
public class SalesforceController {

    private final SalesforceService salesforceService;
    private final EventPublisher publisher;

    public SalesforceController(SalesforceService service, EventPublisher publisher) {
        this.salesforceService = service;
        this.publisher = publisher;
    }

    @GetMapping("/account/{id}")
    public Mono<String> fetchAndPublish(@PathVariable String id) {
        return salesforceService.fetchAccountData(id)
                .doOnNext(publisher::publish);
    }
}

