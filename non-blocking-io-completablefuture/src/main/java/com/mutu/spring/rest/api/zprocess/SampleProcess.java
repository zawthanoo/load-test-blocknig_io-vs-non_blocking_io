package com.mutu.spring.rest.api.zprocess;

import org.springframework.stereotype.Service;
import com.mutu.spring.rest.api.dto.Constant;
import com.mutu.spring.rest.api.dto.Message;

@Service
public class SampleProcess {
    public String virtualProcess(Message message) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + Constant.PROCESSING_TIME;
        double result = 0.0;
        while (System.currentTimeMillis() < endTime) {
            // Simulate some CPU-intensive work
            result = Math.sqrt(Math.random() * 100000);
            // Just to simulate work, can perform any CPU-heavy task here
        }
        return message.getMessage() + ":updated" + ":" + result;
    }
}
