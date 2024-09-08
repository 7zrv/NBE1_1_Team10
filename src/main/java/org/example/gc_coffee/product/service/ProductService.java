package org.example.gc_coffee.product.service;


import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.exception.BadRequestException;
import org.example.gc_coffee.global.exception.DuplicateException;
import org.example.gc_coffee.product.entity.Product;
import org.example.gc_coffee.product.repository.ProductRepository;
import org.example.gc_coffee.product.requestDto.CreateProductRequestDto;
import org.example.gc_coffee.product.requestDto.UpdateProductRequestDto;
import org.example.gc_coffee.product.responseDto.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.example.gc_coffee.global.exception.ExceptionCode.DUPLICATED_PRODUCT_NAME;
import static org.example.gc_coffee.global.exception.ExceptionCode.NOT_FOUND_PRODUCT_ID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    //상픔 등록 메서드
    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {

        checkProductNameDuplicate(requestDto.getProductName());

        Product product = requestDto.toEntity();

        productRepository.save(product);

        return ProductResponseDto.from(product);
    }

    //상품명 중복체크 메서드
    private void checkProductNameDuplicate(String productName) {
        if (productRepository.existsByProductName(productName)) {
            throw new DuplicateException(DUPLICATED_PRODUCT_NAME);
        }
    }

    //전체 상품 목록 반환 메서드
    public List<ProductResponseDto> getAllProduct() {

        List<Product> products = productRepository.findAll();


        return products.stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    //상품 정보 업데이트 메서드
    public ProductResponseDto updateProductInfo(UUID productId, UpdateProductRequestDto requestDto) {

        Product product = getProductById(productId);

        product.updateInfo(requestDto);

        productRepository.save(product);

        return ProductResponseDto.from(product);
    }

    //pk를 이용한 상품 단건 조회 메서드
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_PRODUCT_ID));
    }



}
