package ua.opnu.labwork2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.opnu.labwork2.OrderStatus;
import ua.opnu.labwork2.repository.CategoryRepository;
import ua.opnu.labwork2.repository.CustomerRepository;
import ua.opnu.labwork2.repository.OrderRepository;
import ua.opnu.labwork2.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalyticsService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public long getProductsCount() {
        return productRepository.count();
    }

    public long getCustomersCount() {
        return customerRepository.count();
    }

    public long getActiveOrdersCount() {
        return orderRepository.countByStatus(OrderStatus.PROCESSING) + orderRepository.countByStatus(OrderStatus.PENDING);
    }

    public long getCompletedOrdersCount() {
        return orderRepository.countByStatus(OrderStatus.COMPLETED);
    }

    public String getProductsByCategoryCountSummary() {
        return "Загальна кількість категорій: " + categoryRepository.count() + ", загальна кількість товарів: " + productRepository.count();
    }
}