package com.alphadio.servicea.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ServiceBClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = LoggerFactory.getLogger(ServiceBClient.class);

    @CircuitBreaker(name = "serviceB", fallbackMethod = "fallback")
    @Retry(name = "serviceB")
    public String callServiceB() {
        logger.info("Calling Service B...");

        String response = restTemplate.getForObject(
                "http://service-b:8002/process",
                String.class
        );

        // 🔥 FORCE failure detection
        if (response != null && response.contains("failure")) {
            throw new RuntimeException("Service B returned failure");
        }

        return response;
    }
}