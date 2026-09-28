package ua.opnu.labwork2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.opnu.labwork2.Product;
import ua.opnu.labwork2.exception.ResourceNotFoundException;
import ua.opnu.labwork2.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product with ID " + id + " not found"
                        )
                );
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Product with ID " + id + " not found"
            );
        }

        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoriesId(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Product> searchProducts(String query) {
        return productRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        query,
                        query
                );
    }

    @Transactional(readOnly = true)
    public List<Product> advancedSearch(
            String query,
            Double price,
            String category) {

        return productRepository.findAll().stream()
                .filter(p -> query == null
                        || p.getName().toLowerCase().contains(query.toLowerCase())
                        || (p.getDescription() != null
                        && p.getDescription().toLowerCase().contains(query.toLowerCase())))
                .filter(p -> price == null
                        || p.getPrice() <= price)
                .filter(p -> category == null
                        || p.getCategories().stream()
                        .anyMatch(c -> c.getName().equalsIgnoreCase(category)))
                .toList();
    }
}