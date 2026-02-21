package com.alphadio.servicea.service;

import com.alphadio.servicea.client.ServiceBClient;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

    private final ServiceBClient client;

    public WorkflowService(ServiceBClient client) {
        this.client = client;
    }

    public String executeWorkflow() {
        return client.callServiceB();
    }
}