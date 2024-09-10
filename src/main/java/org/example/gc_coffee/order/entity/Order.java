package org.example.gc_coffee.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gc_coffee.global.common.BaseEntity;
import org.example.gc_coffee.order.type.OrderStatus;
import org.example.gc_coffee.orderItem.entity.OrderItem;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {


    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", nullable = false, length = 16)
    private UUID orderId;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "postcode", nullable = false, length = 200)
    private String postcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 50)
    private OrderStatus orderStatus;

    @Builder
    public Order(String email, String address, String postcode) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = OrderStatus.ORDER_PLACED;
    }

}