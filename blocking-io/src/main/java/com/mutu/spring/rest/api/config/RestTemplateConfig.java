package com.mutu.spring.rest.api.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // Default configuration with a custom timeout (example)
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Custom timeout settings (for example, 5 seconds)
        return builder.setConnectTimeout(Duration.ofMillis(60000)) // Connection timeout
                .setReadTimeout(Duration.ofMillis(60000)) // Read timeout
                .build();
    }
}
