package com.mutu.spring.rest.api.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mutu.spring.rest.api.dto.Message;
import com.mutu.spring.rest.api.dto.ResultDto;
import com.mutu.spring.rest.api.zprocess.ExternalHttpCall;
import com.mutu.spring.rest.api.zprocess.FileReader;
import com.mutu.spring.rest.api.zprocess.ReactiveMessageRepository;
import com.mutu.spring.rest.api.zprocess.SampleProcess;
import reactor.core.publisher.Mono;

@Service
public class ReactiveService {

    @Autowired
    private FileReader fileService;

    @Autowired
    private ReactiveMessageRepository reactiveMessageRepository;

    @Autowired
    private SampleProcess sampleProcess;

    @Autowired
    private ExternalHttpCall externalHttpCall;

    public Mono<ResultDto> process(Message message) {
        String data = sampleProcess.virtualProcess(message);
        return Mono.fromCallable(() -> {
            return new ResultDto(message.getMessageId(), data);
        });
    }

    public Mono<ResultDto> ioprocess(Message message) {
        String data = fileService.readFileFromResources();
        return Mono.fromCallable(() -> {
            return new ResultDto(message.getMessageId(), data);
        });
    }

    public Mono<ResultDto> saveMessage(Message message) {
        Mono<Message> monoMessage = reactiveMessageRepository.save(message);
        return monoMessage.map(saveMessage -> {
            return new ResultDto(saveMessage.getId(), saveMessage.getMessageId());
        });
    }

    public Mono<ResultDto> externalProcess(Message message) {
        Mono<Message> monoMessage = externalHttpCall.process(message);
        return monoMessage.map(response -> {
            return new ResultDto(response.getMessageId(), response.getMessage());
        });
    }
}
