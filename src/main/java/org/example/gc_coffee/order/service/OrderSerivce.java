package org.example.gc_coffee.order.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.exception.BadRequestException;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.order.repository.OrderRepository;
import org.example.gc_coffee.order.requestDto.CreateOrderRequestDto;
import org.example.gc_coffee.order.responseDto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.example.gc_coffee.global.exception.ExceptionCode.NOT_FOUND_ORDER_ID;
import static org.example.gc_coffee.global.exception.ExceptionCode.NOT_FOUND_PRODUCT_ID;

@RequiredArgsConstructor
@Service
public class OrderSerivce {

    private final OrderRepository orderRepository;


    //주문 등록 메서드
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto) {

        Order order = requestDto.toEntity();

        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }


    //주문 단건 조회
    public OrderResponseDto getOrder(UUID orderId) {

        Order order = findOrderById(orderId);

        return OrderResponseDto.from(order);
    }

    //PK를 통한 주문 검색
    public Order findOrderById(UUID orderId) {

        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_ORDER_ID));
    }


    public void deleteOrder(UUID orderId) {

        Order order = findOrderById(orderId);

        orderRepository.deleteById(order.getOrderId());
    }

}
