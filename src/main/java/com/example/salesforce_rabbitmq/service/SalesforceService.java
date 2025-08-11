package com.example.salesforce_rabbitmq.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SalesforceService {
    private final WebClient webClient;

    public SalesforceService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://dummyjson.com").build();
    }

    public Mono<String> fetchAccountData(String accountId) {
        return webClient.get()
                .uri("/products/1", accountId)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("Received: " + response));
    }
}
