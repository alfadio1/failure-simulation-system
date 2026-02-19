package com.alphadio.coreapi.interfaces;

import com.alphadio.coreapi.application.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//This is the "HTTP LAYER"
@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/api/demo")
    public String demo() {
        return demoService.callChaosService();
    }
}
