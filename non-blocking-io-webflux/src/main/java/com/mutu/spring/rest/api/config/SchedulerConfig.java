package com.mutu.spring.rest.api.config;

import java.util.concurrent.Executors;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class SchedulerConfig {

    @Value("${cpu-bound-tasks-threads:100}")
    private int cupThread;

    @Value("${io-bound-tasks-threads:100}")
    private int ioThread;

    @Bean
    public Scheduler cpuScheduler() {
       // For CPU-bound tasks
       return Schedulers.newParallel("cpu-parallel", cupThread);
    }

    @Bean
    public Scheduler ioScheduler() {
       // For IO-bound tasks
       return Schedulers.fromExecutor(Executors.newFixedThreadPool(ioThread));
    }

    @PreDestroy
    public void cleanup() {
        // Shut down the scheduler gracefully
        cpuScheduler().dispose(); 
        ioScheduler().dispose();
    }
}