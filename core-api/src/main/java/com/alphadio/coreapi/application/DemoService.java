package com.alphadio.coreapi.application;

import com.alphadio.coreapi.infrastructure.ChaosClient;
import org.springframework.stereotype.Service;

// This is the "business logic": simply means “I want data from chaos service”

@Service
public class DemoService {

    private final ChaosClient chaosClient;

    public DemoService(ChaosClient chaosClient) {
        this.chaosClient = chaosClient;
    }

    public String callChaosService() {
        return chaosClient.pingChaosService();
    }
}
