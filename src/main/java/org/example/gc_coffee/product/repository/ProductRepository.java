package org.example.gc_coffee.product.repository;


import org.example.gc_coffee.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Boolean existsByProductName(String productName);
}
