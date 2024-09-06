package org.example.gc_coffee.product.service;


import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.exception.DuplicateException;
import org.example.gc_coffee.product.entity.Product;
import org.example.gc_coffee.product.repository.ProductRepository;
import org.example.gc_coffee.product.requestDto.CreateProductRequestDto;
import org.example.gc_coffee.product.responseDto.ProductResponseDto;
import org.springframework.stereotype.Service;

import static org.example.gc_coffee.global.exception.ExceptionCode.DUPLICATED_PRODUCT_NAME;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {

        checkProductNameDuplicate(requestDto.getProductName());

        Product product = requestDto.toEntity();

        productRepository.save(product);

        return ProductResponseDto.from(product);
    }

    private void checkProductNameDuplicate(String productName) {
        if (productRepository.existsByProductName(productName)) {
            throw new DuplicateException(DUPLICATED_PRODUCT_NAME);
        }
    }
}
