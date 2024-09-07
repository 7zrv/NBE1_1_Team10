package org.example.gc_coffee.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gc_coffee.global.common.BaseEntity;

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

    @Column(name = "order_status", nullable = false, length = 50)
    private String orderStatus;

    @Builder
    public Order(String email, String address, String postcode, String orderStatus) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
    }

}