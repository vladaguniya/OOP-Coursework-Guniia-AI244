package ua.opnu.labwork2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.Customer;
import ua.opnu.labwork2.Product;
import ua.opnu.labwork2.service.CustomerService;
import ua.opnu.labwork2.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final ProductService productService;
    private final CustomerService customerService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    @GetMapping("/products/advanced")
    public ResponseEntity<List<Product>> advancedSearch(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(productService.advancedSearch(query, price, category));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String query) {
        return ResponseEntity.ok(customerService.searchCustomers(query));
    }
}