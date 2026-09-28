package ua.opnu.labwork2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.OrderItem;
import ua.opnu.labwork2.service.OrderItemService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping("/order-items")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem item) {
        return ResponseEntity.ok(orderItemService.saveOrderItem(item));
    }

    @GetMapping("/order-items")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem item = orderItemService.getOrderItemById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PutMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> updateOrderItemQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        OrderItem updatedItem = orderItemService.updateOrderItemQuantity(id, quantity);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/order-items/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok("Order item with ID " + id + " removed.");
    }

    @GetMapping("/orders/{id}/items")
    public ResponseEntity<List<OrderItem>> getItemsByOrderId(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getItemsByOrderId(id));
    }
}