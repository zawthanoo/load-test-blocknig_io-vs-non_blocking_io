package com.mutu.spring.rest.api.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import com.mutu.spring.rest.api.dto.Message;
import com.mutu.spring.rest.api.dto.ResultDto;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive")
public class ReactiveController {
    @Autowired
    private ReactiveService reactiveService;

    @PostMapping("/process")
    public Mono<ResultDto> process(@RequestBody Message message, ServerWebExchange exchange) {
        Mono<ResultDto> resultMono = reactiveService.process(message);
        return resultMono.doOnSuccess(data -> {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
        });
    }

    @PostMapping("/ioprocess")
    public Mono<ResultDto> ioprocess(@RequestBody Message message, ServerWebExchange exchange) {
        Mono<ResultDto> resultMono = reactiveService.ioprocess(message);
        return resultMono.doOnSuccess(data -> {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
        });
    }

    @PostMapping("/mongotest")
    public Mono<ResultDto> saveMessage(@RequestBody Message message, ServerWebExchange exchange) {
        Mono<ResultDto> resultMono = reactiveService.saveMessage(message);
        return resultMono.doOnSuccess(data -> {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
        });
    }

    @PostMapping("/external")
    public Mono<ResultDto> externalProcess(@RequestBody Message message, ServerWebExchange exchange) {
        Mono<ResultDto> resultMono = reactiveService.externalProcess(message);
        return resultMono.doOnSuccess(data -> {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
        });
    }
}
