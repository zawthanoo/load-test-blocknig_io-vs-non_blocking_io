package com.mutu.spring.rest.api.zprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.mutu.spring.rest.api.dto.Message;
import reactor.core.publisher.Mono;

@Service
public class ExternalHttpCall {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Message> process(Message message) {
        return webClientBuilder.baseUrl("http://localhost:7084/external")
                .defaultHeader("Content-Type", "application/json")
                .build()
                .post()
                .uri("/api/process")
                .bodyValue(message)
                .retrieve()
                .bodyToMono(Message.class);
    }
}
