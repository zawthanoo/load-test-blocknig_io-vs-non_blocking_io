package com.mutu.spring.rest.api.future;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.mutu.spring.rest.api.dto.Message;
import com.mutu.spring.rest.api.dto.ResultDto;
import com.mutu.spring.rest.api.zprocess.ExternalHttpCall;
import com.mutu.spring.rest.api.zprocess.FileReader;
import com.mutu.spring.rest.api.zprocess.MessageRepository;
import com.mutu.spring.rest.api.zprocess.SampleProcess;

@Service
public class FutureService {
    @Autowired
    private FileReader fileService;

    @Autowired
    private MessageRepository messageRepository;

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
        Message saveMessage = messageRepository.save(message);
        return CompletableFuture.completedFuture(
                new ResultDto(saveMessage.getMessageId(), saveMessage.getMessage()));
    }

    @Async
    public CompletableFuture<ResultDto> externalProcess(Message message) {
        CompletableFuture<Message> futureMessage = externalHttpCall.process(message);
        return futureMessage.thenApply(parm -> {
            return new ResultDto(parm.getMessageId(), parm.getMessage());
        });
    }
}
