package com.mutu.spring.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mutu.spring.rest.api.dto.Message;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/process")
    public ResponseEntity<Message> process(@RequestBody Message message) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 200;
        double result = 0.0;
        while (System.currentTimeMillis() < endTime) {
            // Simulate some CPU-intensive work
            result = Math.sqrt(Math.random() * 100000);
            // Just to simulate work, can perform any CPU-heavy task here
        }
        String data = message.getMessage() + ":updated" + ":" + result;
        return ResponseEntity.ok(new Message(message.getMessageId(), data));
    }}
