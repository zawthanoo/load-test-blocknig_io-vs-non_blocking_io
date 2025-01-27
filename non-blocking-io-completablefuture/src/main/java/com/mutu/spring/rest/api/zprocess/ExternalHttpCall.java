package com.mutu.spring.rest.api.zprocess;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mutu.spring.rest.api.dto.Message;

@Service
public class ExternalHttpCall {
    
    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<Message> process(Message message) {
        String url = "http://localhost:7084/external//api/process";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        
        HttpEntity<Message> requestEntity = new HttpEntity<>(message, headers);
        Message result = restTemplate.postForEntity(url, requestEntity, Message.class).getBody();
        return CompletableFuture.completedFuture(result);
    }
}

