package ua.opnu.labwork2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.opnu.labwork2.OrderItem;
import ua.opnu.labwork2.repository.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItem saveOrderItem(OrderItem item) {
        return orderItemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItem updateOrderItemQuantity(Long id, Integer quantity) {
        OrderItem item = orderItemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setQuantity(quantity);
            return orderItemRepository.save(item);
        }
        return null;
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<OrderItem> getItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}