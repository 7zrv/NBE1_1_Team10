package org.example.gc_coffee.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.gc_coffee.global.common.BaseEntity;

import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", columnDefinition = "BiINARY(16)")
    private UUID productId;

    @Column(name = "product_name", nullable = false, length = 20)
    private String productName;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "description", length = 500)
    private String description;

    @Builder
    public Product(String productName, String category, Long price, String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }

}