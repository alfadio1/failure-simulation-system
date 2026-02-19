package com.alphadio.coreapi.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



// This is the " external service calls": how to talk to external systems (HTTP calls, URLs, retries)
// If tomorrow we change: HTTP -> gRPC; localhost -> AWS service; add retry/circuit breaker:
// only ChaosClient changes.
// DemoService stays clean.

@Component
public class ChaosClient {

    private static final Logger logger = LoggerFactory.getLogger(ChaosClient.class);

    private final RestTemplate restTemplate;

    public ChaosClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "chaosService", fallbackMethod = "fallbackPing")
    @Retry(name = "chaosService")
    public String pingChaosService() {
        logger.info("Calling chaos service");
        return restTemplate.getForObject(
                "http://localhost:8001/chaos/ping",
                String.class
        );
    }

    public String fallbackPing(Exception ex) {
        logger.warn("Chaos service unavailable. Returning fallback. Reason: {}", ex.getMessage());
        return "{\"message\":\"fallback response - chaos service unavailable\"}";
    }
}
