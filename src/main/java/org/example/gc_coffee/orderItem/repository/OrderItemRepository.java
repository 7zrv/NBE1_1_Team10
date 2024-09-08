package org.example.gc_coffee.orderItem.repository;


import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.orderItem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<List<OrderItem>> findByOrder(Order order);

}
