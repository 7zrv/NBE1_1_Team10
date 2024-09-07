package org.example.gc_coffee.product.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.common.response.ApiResponse;
import org.example.gc_coffee.product.requestDto.CreateProductRequestDto;
import org.example.gc_coffee.product.requestDto.UpdateProductRequestDto;
import org.example.gc_coffee.product.responseDto.ProductResponseDto;
import org.example.gc_coffee.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    private final ProductService productService;

    //상품등록 컨트롤러
    @PostMapping("")
    public ApiResponse<ProductResponseDto> createProduct(@RequestBody @Valid CreateProductRequestDto requestDto) {

        ProductResponseDto responseDto = productService.createProduct(requestDto);

        return ApiResponse.ok(201, responseDto, "상품 등록 성공");
    }

    //상품 전체목록 조회 컨트롤러
    @GetMapping("")
    public ApiResponse<List<ProductResponseDto>> getAllProducts() {

        List<ProductResponseDto> responseDtos = productService.getAllProduct();

        return ApiResponse.ok(200, responseDtos, "상품 목록 요청 성공");
    }

    //상품 정보 변경 컨트롤러
    @PutMapping("/{product_id}")
    public ApiResponse<ProductResponseDto> updateProductInfo(@RequestBody @Valid UpdateProductRequestDto requestDto, @PathVariable UUID product_id){

        ProductResponseDto responseDto = productService.updateProductInfo(product_id, requestDto);

        return ApiResponse.ok(200, responseDto, "상품 정보 변경 성공");
    }


}
