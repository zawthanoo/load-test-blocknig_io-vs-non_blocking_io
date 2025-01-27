package com.mutu.spring.rest.api.future;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mutu.spring.rest.api.dto.Message;
import com.mutu.spring.rest.api.dto.ResultDto;

@RestController
@RequestMapping("/future")
public class FutureController {
    @Autowired
    private FutureService futureService;

    @PostMapping("/process")
    public CompletableFuture<ResponseEntity<ResultDto>> process(@RequestBody Message message) {
        CompletableFuture<ResultDto> resultFuture = futureService.process(message);
        return resultFuture.thenApply(result -> ResponseEntity.ok(result));
    }

    @PostMapping("/ioprocess")
    public CompletableFuture<ResponseEntity<ResultDto>> ioprocess(@RequestBody Message message) {
        CompletableFuture<ResultDto> resultFuture = futureService.ioprocess(message);
        return resultFuture.thenApply(result -> ResponseEntity.ok(result));
    }

    @PostMapping("/mongotest")
    public CompletableFuture<ResponseEntity<ResultDto>> saveMessage(@RequestBody Message message) {
        CompletableFuture<ResultDto> resultFuture = futureService.saveMessage(message);
        return resultFuture.thenApply(result -> ResponseEntity.ok(result));
    }

    @PostMapping("/external")
    public CompletableFuture<ResponseEntity<ResultDto>> externalProcess(@RequestBody Message message) {
        CompletableFuture<ResultDto> resultFuture = futureService.externalProcess(message);
        return resultFuture.thenApply(result -> ResponseEntity.ok(result));
    }
}