package com.mutu.spring.rest.api.zprocess;

import org.springframework.stereotype.Service;
import com.mutu.spring.rest.api.dto.Constant;
import com.mutu.spring.rest.api.dto.Message;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SampleProcess {

    @Autowired
    private Scheduler cpuScheduler;
    
    @Autowired
    private Scheduler ioScheduler;

    public Mono<String> virtualProcess(Message message) {
        return Mono.fromCallable(() -> {
            long startTime = System.currentTimeMillis();
            long endTime = startTime + Constant.PROCESSING_TIME;
            double result = 0.0;

            while (System.currentTimeMillis() < endTime) {
                // Simulate some CPU-intensive work
                result = Math.sqrt(Math.random() * 100000);
            }

            return message.getMessage() + ":updated" + ":" + result;
        }).subscribeOn(cpuScheduler); 
    }
}
