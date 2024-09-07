package org.example.gc_coffee.order.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.common.response.ApiResponse;
import org.example.gc_coffee.order.requestDto.CreateOrderRequestDto;
import org.example.gc_coffee.order.responseDto.OrderResponseDto;
import org.example.gc_coffee.order.service.OrderSerivce;
import org.example.gc_coffee.product.requestDto.CreateProductRequestDto;
import org.example.gc_coffee.product.responseDto.ProductResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    private final OrderSerivce orderSerivce;

    //주문등록 컨트롤러
    @PostMapping("")
    public ApiResponse<OrderResponseDto> createOrder(@RequestBody @Valid CreateOrderRequestDto requestDto) {

        OrderResponseDto responseDto = orderSerivce.createOrder(requestDto);

        return ApiResponse.ok(201, responseDto, "14시이후 주문은 익일 14시에 배송됩니다.");
    }




}
