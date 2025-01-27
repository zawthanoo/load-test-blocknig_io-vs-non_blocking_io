package com.mutu.spring.rest.api.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.mutu.spring.rest.api.dto.Message;
import com.mutu.spring.rest.api.dto.ResultDto;
import com.mutu.spring.rest.api.zprocess.ExternalHttpCall;
import com.mutu.spring.rest.api.zprocess.FileReader;
import com.mutu.spring.rest.api.zprocess.ReactiveMessageRepository;
import com.mutu.spring.rest.api.zprocess.SampleProcess;
import reactor.core.publisher.Mono;

@Service
public class FutureService {
    @Autowired
    private FileReader fileService;

    @Autowired
    private ReactiveMessageRepository reactiveMessageRepository;

    @Autowired
    private SampleProcess sampleProcess;

    @Autowired
    private ExternalHttpCall externalHttpCall;


    @Async
    public CompletableFuture<ResultDto> process(Message message) {
        String data = sampleProcess.virtualProcess(message);
        return CompletableFuture.completedFuture(new ResultDto(message.getMessageId(), data));
    }

    @Async
    public CompletableFuture<ResultDto> ioprocess(Message message) {
        String data = fileService.readFileFromResources();
        return CompletableFuture.completedFuture(new ResultDto(message.getMessageId(), data));
    }

    @Async
    public CompletableFuture<ResultDto> saveMessage(Message message) {
        CompletableFuture<Message> futureMessage =
                reactiveMessageRepository.save(message).toFuture();
        Message saveMessage = null;
        try {
            saveMessage = futureMessage.get();
            return CompletableFuture.completedFuture(
                    new ResultDto(saveMessage.getMessageId(), saveMessage.getMessageId()));
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert data");
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to insert data");
        }
    }

    @Async
    public CompletableFuture<ResultDto> externalProcess(Message message) {
        Mono<Message> monoMessage = externalHttpCall.process(message);
        Mono<ResultDto> resultDtoMono =  monoMessage.map(response -> {
            return new ResultDto(response.getMessageId(), response.getMessage());
        });
        return resultDtoMono.toFuture();
    }
}
