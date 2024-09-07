package org.example.gc_coffee.order.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.order.repository.OrderRepository;
import org.example.gc_coffee.order.requestDto.CreateOrderRequestDto;
import org.example.gc_coffee.order.responseDto.OrderResponseDto;
import org.springframework.stereotype.Service;

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


}
