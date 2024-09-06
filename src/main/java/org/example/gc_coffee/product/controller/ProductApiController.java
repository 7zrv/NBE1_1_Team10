package org.example.gc_coffee.product.controller;


import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.common.response.ApiResponse;
import org.example.gc_coffee.product.requestDto.CreateProductRequestDto;
import org.example.gc_coffee.product.responseDto.ProductResponseDto;
import org.example.gc_coffee.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    private final ProductService productService;

    @PostMapping("")
    public ApiResponse<ProductResponseDto> registerUser(@RequestBody CreateProductRequestDto requestDto) {

        ProductResponseDto responseDto = productService.createProduct(requestDto);

        return ApiResponse.ok(201, responseDto, "상품 등록 성공");
    }
}
