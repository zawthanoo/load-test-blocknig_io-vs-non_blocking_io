package com.mutu.spring.rest.api.traditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mutu.spring.rest.api.dto.Message;
import com.mutu.spring.rest.api.dto.ResultDto;
import com.mutu.spring.rest.api.zprocess.ExternalHttpCall;

@RestController
@RequestMapping("/traditional")
public class TraditionalController {
    @Autowired
    private TraditionanlService traditionanlService;

    @Autowired
    private ExternalHttpCall externalHttpCall;

    @PostMapping("/process")
    public ResponseEntity<ResultDto> process(@RequestBody Message message) {
        ResultDto result = traditionanlService.process(message);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/ioprocess")
    public ResponseEntity<ResultDto> ioprocess(@RequestBody Message message) {
        ResultDto result = traditionanlService.ioprocess(message);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/mongotest")
    public ResponseEntity<ResultDto> saveMessage(@RequestBody Message message) {
        ResultDto result = traditionanlService.saveMessage(message);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/external")
    public ResponseEntity<ResultDto> externalProcess(@RequestBody Message message) {
        Message response = externalHttpCall.process(message);
        return ResponseEntity.ok(new ResultDto(response.getMessageId(), response.getMessage()));
    }
}
