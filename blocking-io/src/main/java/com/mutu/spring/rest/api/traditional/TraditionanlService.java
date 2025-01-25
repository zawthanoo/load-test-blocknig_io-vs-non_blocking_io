package com.mutu.spring.rest.api.traditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mutu.spring.rest.api.dto.Message;
import com.mutu.spring.rest.api.dto.ResultDto;
import com.mutu.spring.rest.api.zprocess.FileService;
import com.mutu.spring.rest.api.zprocess.MessageRepository;
import com.mutu.spring.rest.api.zprocess.SampleProcess;

@Service
public class TraditionanlService {
    @Autowired
    private FileService fileService;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private SampleProcess sampleProcess;

    public ResultDto process(Message message) {
        String data = sampleProcess.virtualProcess(message);
        return new ResultDto(message.getMessageId(), data);
    }

    public ResultDto ioprocess(Message message) {
        String data = fileService.readFileFromResources();
        return new ResultDto(message.getMessageId(), data);
    }

    public ResultDto saveMessage(Message message) {
        Message saveMessage = messageRepo.insert(message);
        return new ResultDto(saveMessage.getMessageId(), saveMessage.getMessageId());
    }
}
