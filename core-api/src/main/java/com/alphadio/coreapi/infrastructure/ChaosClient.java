package com.alphadio.coreapi.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;


// This is the " external service calls": how to talk to external systems (HTTP calls, URLs, retries)
// If tomorrow we change: HTTP -> gRPC; localhost -> AWS service; add retry/circuit breaker:
// only ChaosClient changes.
// DemoService stays clean.

@Component
public class ChaosClient {

    private static final Logger logger = LoggerFactory.getLogger(ChaosClient.class);

    private final RestTemplate restTemplate;

    private final Counter fallbackCounter;

    public ChaosClient(RestTemplate restTemplate, MeterRegistry meterRegistry) {
        this.restTemplate = restTemplate;
        this.fallbackCounter = Counter.builder("fallback_calls_total")
                .description("Number of fallback calls")
                .register(meterRegistry);
    }

    @CircuitBreaker(name = "chaosService", fallbackMethod = "fallbackPing")
    @Retry(name = "chaosService")
    public String pingChaosService() {
        logger.info("Calling chaos service");
        return restTemplate.getForObject(
                "http://service-a:8087/workflow",
                String.class
        );
    }

    public String fallbackPing(Exception e) {
        fallbackCounter.increment();
        logger.warn("Fallback triggered: {}", e.getMessage());
        return "fallback response - chaos service unavailable";
    }
}
