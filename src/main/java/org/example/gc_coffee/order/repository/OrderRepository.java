package org.example.gc_coffee.order.repository;

import org.example.gc_coffee.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {


}
