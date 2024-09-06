package org.example.gc_coffee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.gc_coffee.global.common.BaseEntity;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long id;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;



}