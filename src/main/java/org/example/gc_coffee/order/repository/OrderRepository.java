package org.example.gc_coffee.order.repository;

import jakarta.transaction.Transactional;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.order.type.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByOrderId(UUID orderId);

    @Modifying //벌크 연산을 위한 어노테이션
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = :status WHERE o.updatedAt BETWEEN :start AND :end")
    void updateOrderStatus(@Param("status") OrderStatus status,
                           @Param("start") LocalDateTime start,
                           @Param("end") LocalDateTime end);
}
