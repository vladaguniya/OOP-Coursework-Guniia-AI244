package ua.opnu.labwork2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator")
public class ActuatorController {

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok("{\"status\":\"UP\"}");
    }

    @GetMapping("/metrics")
    public ResponseEntity<String> getMetrics() {
        return ResponseEntity.ok("Service metrics are stable.");
    }

    @GetMapping("/prometheus")
    public ResponseEntity<String> getPrometheus() {
        return ResponseEntity.ok("Prometheus metrics endpoint stub.");
    }
}