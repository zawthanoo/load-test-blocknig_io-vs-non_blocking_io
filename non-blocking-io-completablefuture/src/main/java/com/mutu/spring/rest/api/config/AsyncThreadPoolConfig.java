package com.mutu.spring.rest.api.config;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;


// @Configuration
// @EnableAsync
@Slf4j
public class AsyncThreadPoolConfig {

    @Value("${initial-thread-size:100}")
    private int corePoolSize;

    @Value("${max-thread-size:500}")
    private int maxPoolSize;

    @Bean(name = "asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        log.info("Creating Async Task Executor for OTCServiceApi to serve request in parallel threads");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);  // Initial number of threads
        executor.setMaxPoolSize(maxPoolSize); // Maximum number of threads
        //executor.setQueueCapacity(500); //Queue size for holding pending tasks. Keep this commented else throws error if queue is full.
        executor.setThreadNamePrefix("AsyncTask-");
        executor.initialize();
        return executor;
    }

}