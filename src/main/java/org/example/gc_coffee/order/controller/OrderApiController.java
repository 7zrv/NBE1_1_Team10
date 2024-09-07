package org.example.gc_coffee.order.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.common.response.ApiResponse;
import org.example.gc_coffee.order.requestDto.CreateOrderRequestDto;
import org.example.gc_coffee.order.responseDto.OrderResponseDto;
import org.example.gc_coffee.order.service.OrderSerivce;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    private final OrderSerivce orderSerivce;

    //주문등록 컨트롤러
    @PostMapping("")
    public ApiResponse<OrderResponseDto> createOrder(@RequestBody @Valid CreateOrderRequestDto requestDto) {

        OrderResponseDto responseDto = orderSerivce.createOrder(requestDto);

        return ApiResponse.ok(201, responseDto, "상품 주문 성공");
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponseDto> getOrder(@PathVariable UUID orderId) {

        OrderResponseDto responseDto = orderSerivce.getOrder(orderId);

        return ApiResponse.ok(200, responseDto, "주문 조회 성공");
    }


}
