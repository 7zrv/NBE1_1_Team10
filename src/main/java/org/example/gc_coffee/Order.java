package org.example.gc_coffee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.gc_coffee.global.common.BaseEntity;

import java.time.Instant;

@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @Column(name = "order_id", nullable = false, length = 16)
    private String orderId;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "postcode", nullable = false, length = 200)
    private String postcode;

    @Column(name = "order_status", nullable = false, length = 50)
    private String orderStatus;



}