package com.alphadio.servicea.controller;

import com.alphadio.servicea.service.WorkflowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowController {

    private final WorkflowService service;

    public WorkflowController(WorkflowService service) {
        this.service = service;
    }

    @GetMapping("/workflow")
    public String run() {
        return service.executeWorkflow();
    }
}