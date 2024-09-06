package org.example.gc_coffee.product.responseDto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.product.entity.Product;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ProductResponseDto {

    private final UUID id;

    private final String productName;

    private final String category;

    private final Long price;

    private final String description;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(product.getProductId() ,product.getProductName(), product.getCategory(), product.getPrice(), product.getDescription());
    }
}
