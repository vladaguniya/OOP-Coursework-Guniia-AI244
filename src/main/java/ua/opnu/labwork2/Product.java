package ua.opnu.labwork2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Назва товару не може бути порожньою")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Ціна є обов'язковою")
    @Positive(message = "Ціна повинна бути більшою за 0")
    private Double price;

    @NotNull(message = "Кількість товару є обов'язковою")
    @Min(value = 0, message = "Кількість товару не може бути від'ємною")
    private Integer stockQuantity;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Order> orders;
}