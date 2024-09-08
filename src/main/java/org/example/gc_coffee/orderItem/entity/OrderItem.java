package org.example.gc_coffee.orderItem.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gc_coffee.global.common.BaseEntity;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.product.entity.Product;


@NoArgsConstructor
@Getter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Builder
    public OrderItem( Long price, Integer quantity, Order order, Product product) {
        this.category = product.getCategory();
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }



}