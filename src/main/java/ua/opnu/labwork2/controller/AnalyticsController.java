package ua.opnu.labwork2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.service.AnalyticsService;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/products/count")
    public ResponseEntity<Long> getProductsCount() {
        return ResponseEntity.ok(analyticsService.getProductsCount());
    }

    @GetMapping("/customers/count")
    public ResponseEntity<Long> getCustomersCount() {
        return ResponseEntity.ok(analyticsService.getCustomersCount());
    }

    @GetMapping("/orders/active")
    public ResponseEntity<Long> getActiveOrdersCount() {
        return ResponseEntity.ok(analyticsService.getActiveOrdersCount());
    }

    @GetMapping("/orders/completed")
    public ResponseEntity<Long> getCompletedOrdersCount() {
        return ResponseEntity.ok(analyticsService.getCompletedOrdersCount());
    }

    @GetMapping("/products/by-category")
    public ResponseEntity<String> getProductsByCategoryCount() {
        return ResponseEntity.ok(analyticsService.getProductsByCategoryCountSummary());
    }
}